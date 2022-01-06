package co.com.Biblioteca.Router.Recurso;

import co.com.Biblioteca.Collections.Recurso;
import co.com.Biblioteca.Enums.AreaTematica;
import co.com.Biblioteca.Enums.TipoRecurso;
import co.com.Biblioteca.Mapper.RecursoMapper;
import co.com.Biblioteca.Repository.RecursoRepository;
import co.com.Biblioteca.UseCase.Recurso.UseCaseDisponibilidadRecurso;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@WebFluxTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {DisponibilidadRecursoRouter.class, UseCaseDisponibilidadRecurso.class, RecursoMapper.class})
class DisponibilidadRecursoRouterTest {
    @MockBean
    private RecursoRepository repositorio;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void DisponibilidadRecursoTest(){
        Recurso recurso = new Recurso();
        recurso.setNombre("Nombre");
        recurso.setAreaTematica(AreaTematica.HISTORIA);
        recurso.setTipoRecurso(TipoRecurso.LIBRO);
        recurso.setId("WWW");
        recurso.setFechaPrestamo(new Date(55555555));
        recurso.setDisponible(false);

        when(repositorio.findById(recurso.getId())).thenReturn(Mono.just(recurso));


        webTestClient.get()
                .uri("/recurso/disponibilidad/{id}", "WWW")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .value(userResponse -> {
                            Assertions.assertThat(userResponse)
                                    .isEqualTo("El recurso " + recurso.getNombre() + " no esta disponible, y se presto el: " + recurso.getFechaPrestamo());
                        }
                );

    }
}