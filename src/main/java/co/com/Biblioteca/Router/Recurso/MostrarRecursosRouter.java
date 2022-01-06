
package co.com.Biblioteca.Router.Recurso;

import co.com.Biblioteca.Dto.RecursoDto;
import co.com.Biblioteca.UseCase.Recurso.UseCaseMostrarRecursos;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class MostrarRecursosRouter {

    @Bean
    public RouterFunction<ServerResponse> mostrarRecurso(UseCaseMostrarRecursos useCaseMostrarRecursos) {
        return route(
                GET("/recursos").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(useCaseMostrarRecursos.get(), RecursoDto.class))
                        .onErrorResume((error)->ServerResponse.badRequest().build())
        );
    }

}

