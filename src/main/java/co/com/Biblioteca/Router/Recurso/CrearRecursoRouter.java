package co.com.Biblioteca.Router.Recurso;

import co.com.Biblioteca.Dto.RecursoDto;
import co.com.Biblioteca.UseCase.Recurso.UseCaseCrear;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class CrearRecursoRouter {
   @Bean
    public RouterFunction<ServerResponse> crearRecurso(UseCaseCrear useCaseCrear) {
        return route(POST("/recurso/crear").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(RecursoDto.class)
                        .flatMap(recursoDTO -> useCaseCrear.apply(recursoDTO)
                                .flatMap(result -> ServerResponse.ok()
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .bodyValue(result))
                        )
        );
    }
}

