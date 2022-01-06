package co.com.Biblioteca.Router.Recurso;

import co.com.Biblioteca.Dto.RecursoDto;
import co.com.Biblioteca.UseCase.Recurso.UseCaseActualizar;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.PUT;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class ActualizarRecursoRouter {
    @Bean
    public RouterFunction<ServerResponse> actualziarRecurso(UseCaseActualizar useCaseActualizar) {
        return route(PUT("/recurso/actualizar").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(RecursoDto.class)
                        .flatMap(recursoDTO -> useCaseActualizar.apply(recursoDTO)
                                .flatMap(result -> ServerResponse.ok()
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .bodyValue(result))
                        )
        );
    }
}