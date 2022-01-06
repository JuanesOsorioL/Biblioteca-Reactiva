
package co.com.Biblioteca.Router.Recurso;

import co.com.Biblioteca.Dto.RecursoDto;
import co.com.Biblioteca.UseCase.Recurso.UseCaseElminar;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class EliminarRecursoRouter {
   @Bean
    public RouterFunction<ServerResponse> borrarRecurso(UseCaseElminar useCaseElminar) {
        return route(DELETE("/recurso/eliminar/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.accepted()
                        .contentType(MediaType.TEXT_PLAIN)
                        .body(BodyInserters
                                .fromPublisher(useCaseElminar
                                        .apply(request.pathVariable("id")), String.class))
                        .onErrorResume(throwable -> ServerResponse.badRequest().body(throwable.getMessage(),String.class))
               /* request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(useCaseElminar.deleteById(request.pathVariable("id")), RecursoDto.class)*/
        );
    }
}

