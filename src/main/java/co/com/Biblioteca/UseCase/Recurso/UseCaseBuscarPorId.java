package co.com.Biblioteca.UseCase.Recurso;

import co.com.Biblioteca.Dto.RecursoDto;
import co.com.Biblioteca.Mapper.RecursoMapper;
import co.com.Biblioteca.Repository.RecursoRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Service
@Validated
public class UseCaseBuscarPorId implements BuscarRecurso{
    private final RecursoRepository repositorio;
    private final RecursoMapper mapper;

    public UseCaseBuscarPorId(RecursoRepository repositorioRecurso, RecursoMapper recursoMapper) {
        this.repositorio = repositorioRecurso;
        this.mapper = recursoMapper;
    }

    @Override
    public Mono<RecursoDto> findById(String id) {
        return repositorio.findById(id).map(mapper.mapRecursoToDTO());
    }
}

