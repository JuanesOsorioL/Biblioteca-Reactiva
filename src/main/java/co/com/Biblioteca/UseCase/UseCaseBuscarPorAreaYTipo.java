package co.com.Biblioteca.UseCase;

import co.com.Biblioteca.Dto.RecursoDto;
import co.com.Biblioteca.Enums.AreaTematica;
import co.com.Biblioteca.Enums.TipoRecurso;
import co.com.Biblioteca.Mapper.RecursoMapper;
import co.com.Biblioteca.Repository.RecursoRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;


@Service
@Validated
public class UseCaseBuscarPorAreaYTipo implements ObtenerPorAreaYTipo{
    private final RecursoRepository repositorio;
    private final RecursoMapper mapper;

    public UseCaseBuscarPorAreaYTipo(RecursoRepository repositorio, RecursoMapper mapper) {
        this.repositorio = repositorio;
        this.mapper = mapper;
    }

    @Override
    public Flux<RecursoDto> get(String area, String tipo) {
        if (area!="" || tipo!=""){
            return repositorio.findByAreaTematicaAndTipoRecurso(AreaTematica.valueOf(area), TipoRecurso.valueOf(tipo))
                    .map(mapper.mapRecursoToDTO());
        }else{
            return repositorio.findByAreaTematicaOrTipoRecurso(AreaTematica.valueOf(area), TipoRecurso.valueOf(tipo))
                    .map(mapper.mapRecursoToDTO());
        }

    }
}