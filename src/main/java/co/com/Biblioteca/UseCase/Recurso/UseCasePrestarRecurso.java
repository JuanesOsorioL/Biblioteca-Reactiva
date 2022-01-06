package co.com.Biblioteca.UseCase.Recurso;


import co.com.Biblioteca.Mapper.RecursoMapper;
import co.com.Biblioteca.Repository.RecursoRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.function.Function;

@Service
@Validated
public class UseCasePrestarRecurso implements Function<String , Mono<String>> {
    private final RecursoRepository repositorio;
    private final RecursoMapper mapper;

    public UseCasePrestarRecurso(RecursoRepository repositorio, RecursoMapper mapper) {
        this.repositorio = repositorio;
        this.mapper = mapper;
    }

    @Override
    public Mono<String> apply(String id) {
        var recursoUpdate= repositorio.findById(id);

        return recursoUpdate.flatMap(
                value->{
                    if(value.isDisponible()){
                        value.setDisponible(false);
                        value.setFechaPrestamo(new Date());
                        return  repositorio.save(value).thenReturn("Recurso prestado");
                    }
                    return Mono.just("NO se puede prestar");
                });
    }
    /*public Mono<String> get(String id) {
        Mono<Recurso> recursoMono = repositorio.findById(id);

        return recursoMono.flatMap(r -> {
            if (r.isDisponible()) {
                r.setDisponible(false);
                r.setFechaPrestamo(LocalDate.now());
                return repositorio.save(r).thenReturn("El recurso fue prestado con exito");
            }
            return Mono.just("El recurso no está disponible");
        });
    }*/
}


