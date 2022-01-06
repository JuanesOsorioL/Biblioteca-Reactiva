package co.com.Biblioteca.UseCase.Recurso;

import co.com.Biblioteca.Mapper.RecursoMapper;
import co.com.Biblioteca.Repository.RecursoRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.function.Function;


@Service
@Validated
public class UseCaseDisponibilidadRecurso implements Function<String, Mono<String>> {
    private final RecursoRepository repositorio;
    private final RecursoMapper mapper;

    public UseCaseDisponibilidadRecurso(RecursoRepository repositorio, RecursoMapper mapper) {
        this.repositorio = repositorio;
        this.mapper = mapper;
    }

    @Override
    public Mono<String> apply(String id) {
        Objects.requireNonNull(id, "El id no puede ser nulo");
        return repositorio.findById(id)
                .map(recurso ->
                        recurso.isDisponible()
                                ? "El recurso " + recurso.getNombre() + " esta disponible"
                                : "El recurso " + recurso.getNombre() + " no esta disponible, y se presto el: " + recurso.getFechaPrestamo()
                );
    }
/*    public Mono<String> get(String id) {
        return repositorio.findById(id).map(r->
                r.isDisponible() ?
                        "El recurso está disponible"
                        : "El recurso no está disponible, fue prestado el: "+ r.getFechaPrestamo()
        );
    }*/
}


