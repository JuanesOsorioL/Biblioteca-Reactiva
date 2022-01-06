package co.com.Biblioteca.UseCase.Recurso;

import co.com.Biblioteca.Collections.Recurso;
import co.com.Biblioteca.Mapper.RecursoMapper;
import co.com.Biblioteca.Repository.RecursoRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
@Validated
public class UseCaseDevolverRecurso implements SaberDisponibilidad{
    private final RecursoRepository repositorio;
    private final RecursoMapper mapper;

    public UseCaseDevolverRecurso(RecursoRepository repositorio, RecursoMapper mapper) {
        this.repositorio = repositorio;
        this.mapper = mapper;
    }

    @Override
    public Mono<String> get(String id) {
        Mono<Recurso> recursoMono = repositorio.findById(id);
        return recursoMono.flatMap(r -> {
            if (!r.isDisponible()) {
                r.setDisponible(true);
                r.setFechaPrestamo(new Date());
                return repositorio.save(r).thenReturn("El recurso fue devuelto con exito");
            }
            return Mono.just("El recurso no está prestado");
        });
    }
}

