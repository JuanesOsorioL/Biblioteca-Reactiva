package co.com.Biblioteca.UseCase.Recurso;

import co.com.Biblioteca.Collections.Recurso;
import co.com.Biblioteca.Dto.RecursoDto;
import reactor.core.publisher.Mono;
@FunctionalInterface
public interface GuardarRecurso {
    Mono<Recurso> apply(RecursoDto recursoDTO);
}

