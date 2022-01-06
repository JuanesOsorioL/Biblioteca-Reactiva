package co.com.Biblioteca.Router.Recurso;

import co.com.Biblioteca.Collections.Recurso;
import co.com.Biblioteca.Enums.AreaTematica;
import co.com.Biblioteca.Enums.TipoRecurso;
import co.com.Biblioteca.Mapper.RecursoMapper;
import co.com.Biblioteca.Repository.RecursoRepository;
import co.com.Biblioteca.UseCase.Recurso.UseCasePrestarRecurso;
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

import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebFluxTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {PrestarRecursoRouter.class, UseCasePrestarRecurso.class, RecursoMapper.class})
class PrestarRecursoRouterTest {
    @MockBean
    private RecursoRepository repositorio;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void PrestarRecursoTest() {
        Recurso recurso1 = new Recurso();
        recurso1.setId("444");
        recurso1.setAreaTematica(AreaTematica.FISICA);
        recurso1.setDisponible(true);
        recurso1.setTipoRecurso(TipoRecurso.LIBRO);
        recurso1.setNombre("Nombree");
        recurso1.setFechaPrestamo(new Date());

        Mono<Recurso> recursoMono = Mono.just(recurso1);

        when(repositorio.findById(recurso1.getId())).thenReturn(recursoMono);
        when(repositorio.save(any())).thenReturn(recursoMono);

        webTestClient.put()
                .uri("/recurso/prestar/444")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .value(userResponse -> {
                            Assertions.assertThat(userResponse.equals("El recurso fue prestado con exito"));
                        }
                );
        Mockito.verify(repositorio,Mockito.times(1)).findById("444");

    }
}