package co.com.Biblioteca.UseCase;

import co.com.Biblioteca.Dto.RecursoDto;
import reactor.core.publisher.Flux;

public interface ObtenerPorAreaYTipo {
    Flux<RecursoDto> get(String area, String tipo);
}