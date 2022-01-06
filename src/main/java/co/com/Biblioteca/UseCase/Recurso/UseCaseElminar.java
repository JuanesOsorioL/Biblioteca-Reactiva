package co.com.Biblioteca.UseCase.Recurso;

import co.com.Biblioteca.Mapper.RecursoMapper;
import co.com.Biblioteca.Repository.RecursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.function.Function;

@Service
@Validated
public class UseCaseElminar implements Function<String, Mono<String>> {
    private final RecursoRepository repositorio;
    private final RecursoMapper mapper;

    @Autowired
    public UseCaseElminar(RecursoRepository repositorio, RecursoMapper mapper) {
        this.repositorio = repositorio;
        this.mapper = mapper;
    }

    @Override
    public Mono<String> apply(String id) {
        Objects.requireNonNull(id, "Id es requerido");
        return repositorio.findById(id)
                .flatMap(recurso -> {
                    return repositorio.deleteById(recurso.getId()).thenReturn("borrado");
                })
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND , "id erroneo")));
    }
/*    public Mono<Void> deleteById(String id) {
        return repositorio.deleteById(id);
    }*/
}
