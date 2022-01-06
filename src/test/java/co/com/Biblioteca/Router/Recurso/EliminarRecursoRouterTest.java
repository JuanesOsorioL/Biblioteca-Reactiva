package co.com.Biblioteca.Router.Recurso;

import co.com.Biblioteca.Collections.Recurso;
import co.com.Biblioteca.Enums.AreaTematica;
import co.com.Biblioteca.Enums.TipoRecurso;
import co.com.Biblioteca.Mapper.RecursoMapper;
import co.com.Biblioteca.Repository.RecursoRepository;
import co.com.Biblioteca.UseCase.Recurso.UseCaseElminar;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@WebFluxTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {EliminarRecursoRouter.class, UseCaseElminar.class, RecursoMapper.class})
class EliminarRecursoRouterTest {

    @MockBean
    private RecursoRepository repositorio;

    @Autowired
    private WebTestClient webTestClient;

    @SpyBean
    private UseCaseElminar useCaseBorrar;

    @Test
    void testBorrarRecurso() {
        Recurso recurso = new Recurso();
        recurso.setNombre("atomos");
        recurso.setAreaTematica(AreaTematica.CIENCIAS);
        recurso.setTipoRecurso(TipoRecurso.LIBRO);
        recurso.setId("1");

        Mono<Void>  vacio= Mono.empty();

        when(repositorio.findById("1")).thenReturn(Mono.just(recurso));
        when(repositorio.deleteById("1")).thenReturn(vacio);

        webTestClient.delete()
                .uri("/recurso/eliminar/{id}", "1")
                .exchange()
                .expectStatus()
                .isAccepted()
                .expectBody(String.class)
                .value(userResponse -> {
                    Assertions.assertThat(userResponse).isEqualTo("borrado");
                });
    }
}