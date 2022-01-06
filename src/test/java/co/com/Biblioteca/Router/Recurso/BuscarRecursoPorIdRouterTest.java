package co.com.Biblioteca.Router.Recurso;

import co.com.Biblioteca.Collections.Recurso;
import co.com.Biblioteca.Dto.RecursoDto;
import co.com.Biblioteca.Enums.AreaTematica;
import co.com.Biblioteca.Enums.TipoRecurso;
import co.com.Biblioteca.Mapper.RecursoMapper;
import co.com.Biblioteca.Repository.RecursoRepository;
import co.com.Biblioteca.UseCase.Recurso.UseCaseBuscarPorId;
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
@ContextConfiguration(classes= {BuscarRecursoPorIdRouter.class, UseCaseBuscarPorId.class, RecursoMapper.class})
class BuscarRecursoPorIdRouterTest {

    @MockBean
    RecursoRepository repositorio;

    @Autowired
    WebTestClient webTestClient;

    @Test
    public void buscarRecursoPorIdTest(){
        Recurso recurso1 = new Recurso();
        recurso1.setId("123");
        recurso1.setAreaTematica(AreaTematica.HISTORIA);
        recurso1.setDisponible(true);
        recurso1.setTipoRecurso(TipoRecurso.LIBRO);
        recurso1.setNombre("Historia");
        recurso1.setFechaPrestamo(new Date());

        Mono<Recurso> recursoMono = Mono.just(recurso1);

        when(repositorio.findById(recurso1.getId())).thenReturn(recursoMono);

        webTestClient.get()
                .uri("/recurso/buscar/123")
                .exchange()
                .expectStatus().isOk()
                .expectBody(RecursoDto.class)
                .value(userResponse -> {
                            Assertions.assertThat(userResponse.getAreaTematica()).isEqualTo(recurso1.getAreaTematica());
                            Assertions.assertThat(userResponse.getTipoRecurso()).isEqualTo(recurso1.getTipoRecurso());
                            Assertions.assertThat(userResponse.getNombre()).isEqualTo(recurso1.getNombre());
                            Assertions.assertThat(userResponse.isDisponible()).isEqualTo(recurso1.isDisponible());
                            Assertions.assertThat(userResponse.getId()).isEqualTo(recurso1.getId());

                        }
                );

        Mockito.verify(repositorio,Mockito.times(1)).findById("123");
    }
}