/*
package co.com.Biblioteca.UseCase.Tipo;

import co.com.Biblioteca.Dto.RecursoDto;
import co.com.Biblioteca.Mapper.RecursoMapper;
import co.com.Biblioteca.Repository.RecursoRepository;
import co.com.Biblioteca.UseCase.ObtenerPorString;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;


@Service
@Validated
public class UseCaseBuscarPorTipo implements ObtenerPorString {
    private final RecursoRepository repositorio;
    private final RecursoMapper mapper;

    public UseCaseBuscarPorTipo(RecursoRepository repositorio, RecursoMapper mapper) {
        this.repositorio = repositorio;
        this.mapper = mapper;
    }

    @Override
    public Flux<RecursoDto> get(String tipo) {
        return repositorio.findByTipo(tipo).map(mapper.mapRecursoToDTO());
    }
}
*/

