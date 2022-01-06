package co.com.Biblioteca.UseCase.Recurso;

import co.com.Biblioteca.Dto.RecursoDto;
import reactor.core.publisher.Mono;

@FunctionalInterface
public interface BuscarRecurso {
    Mono<RecursoDto> findById(String id);
}

