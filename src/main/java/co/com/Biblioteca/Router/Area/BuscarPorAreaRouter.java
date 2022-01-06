/*package co.com.Biblioteca.Router.Area;

import co.com.Biblioteca.Dto.RecursoDto;
import co.com.Biblioteca.UseCase.Area.UseCaseBucarPorArea;
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
public class BuscarPorAreaRouter {
    @Bean
    public RouterFunction<ServerResponse> buscarPorArea(UseCaseBucarPorArea useCase) {
        return route(
                GET("/recursos/filtrarArea/{area}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(useCase.get(request.pathVariable("area")), RecursoDto.class))
        );
    }
}*/

