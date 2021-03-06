package co.com.Biblioteca.Router.Recurso;

import co.com.Biblioteca.Dto.RecursoDto;
import co.com.Biblioteca.UseCase.Recurso.UseCaseBuscarPorId;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class BuscarRecursoPorIdRouter {
   @Bean
    public RouterFunction<ServerResponse> consultarRecursos(UseCaseBuscarPorId useCase) {
        return route(GET("/recurso/buscar/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request ->
                        ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(useCase.findById(request.pathVariable("id")), RecursoDto.class));
    }
}

