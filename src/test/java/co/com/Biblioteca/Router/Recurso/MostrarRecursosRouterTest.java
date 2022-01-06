package co.com.Biblioteca.Router.Recurso;

import co.com.Biblioteca.Collections.Recurso;
import co.com.Biblioteca.Dto.RecursoDto;
import co.com.Biblioteca.Enums.AreaTematica;
import co.com.Biblioteca.Enums.TipoRecurso;
import co.com.Biblioteca.Mapper.RecursoMapper;
import co.com.Biblioteca.Repository.RecursoRepository;
import co.com.Biblioteca.UseCase.Recurso.UseCaseMostrarRecursos;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

import java.time.LocalDate;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@WebFluxTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {MostrarRecursosRouter.class, UseCaseMostrarRecursos.class, RecursoMapper.class})
class MostrarRecursosRouterTest {
    @MockBean
    private RecursoRepository repositorio;

    @Autowired
    private WebTestClient webTestClient;


    @Test
    public void testGetDatos() {

        Recurso recurso1 = new Recurso();
        recurso1.setId("WWW");
        recurso1.setAreaTematica(AreaTematica.CIENCIAS);
        recurso1.setDisponible(true);
        recurso1.setTipoRecurso(TipoRecurso.LIBRO);
        recurso1.setNombre("AAA");
        recurso1.setFechaPrestamo(new Date());

        Recurso recurso2 = new Recurso();
        recurso2.setId("RRR");
        recurso2.setAreaTematica(AreaTematica.CIENCIAS);
        recurso2.setDisponible(true);
        recurso2.setTipoRecurso(TipoRecurso.LIBRO);
        recurso2.setNombre("SSSS");
        recurso2.setFechaPrestamo(new Date());

        when(repositorio.findAll()).thenReturn(Flux.just(recurso1, recurso2));

        webTestClient.get()
                .uri("/recursos")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(RecursoDto.class)
                .value(userResponse -> {
                            Assertions.assertThat(userResponse.get(0).getNombre()).isEqualTo(recurso1.getNombre());
                            Assertions.assertThat(userResponse.get(1).getNombre()).isEqualTo(recurso2.getNombre());
                        }
                );
    }
}