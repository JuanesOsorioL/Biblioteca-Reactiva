package co.com.Biblioteca.Router.Recurso;

import co.com.Biblioteca.Collections.Recurso;
import co.com.Biblioteca.Dto.RecursoDto;
import co.com.Biblioteca.Enums.AreaTematica;
import co.com.Biblioteca.Enums.TipoRecurso;
import co.com.Biblioteca.Mapper.RecursoMapper;
import co.com.Biblioteca.Repository.RecursoRepository;
import co.com.Biblioteca.UseCase.Recurso.UseCaseCrear;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebFluxTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {CrearRecursoRouter.class, UseCaseCrear.class, RecursoMapper.class})
class CrearRecursoRouterTest {

    @MockBean
    private RecursoRepository repositorio;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void testCrearRecurso() {

        Recurso recurso = new Recurso();
        recurso.setId("xxx");
        recurso.setAreaTematica(AreaTematica.CIENCIAS);
        recurso.setDisponible(true);
        recurso.setTipoRecurso(TipoRecurso.LIBRO);
        recurso.setNombre("Documental");
        recurso.setFechaPrestamo(new Date());

        RecursoDto recursoDto = new RecursoDto(recurso.getId(),
                recurso.getTipoRecurso(),
                recurso.isDisponible(),
                recurso.getAreaTematica(),
                recurso.getNombre(),
                recurso.getFechaPrestamo());

        Mono<Recurso> datoMono = Mono.just(recurso);

        when(repositorio.save(any())).thenReturn(datoMono);

        webTestClient.post()
                .uri("/recurso/crear")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(recursoDto), RecursoDto.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody(RecursoDto.class)
                .value(userResponse -> {
                            Assertions.assertThat(userResponse.getAreaTematica()).isEqualTo(recurso.getAreaTematica());
                            Assertions.assertThat(userResponse.getTipoRecurso()).isEqualTo(recurso.getTipoRecurso());
                            Assertions.assertThat(userResponse.getNombre()).isEqualTo(recurso.getNombre());
                            Assertions.assertThat(userResponse.isDisponible()).isEqualTo(recurso.isDisponible());
                            Assertions.assertThat(userResponse.getId()).isEqualTo(recurso.getId());
                        }
                );
    }


}