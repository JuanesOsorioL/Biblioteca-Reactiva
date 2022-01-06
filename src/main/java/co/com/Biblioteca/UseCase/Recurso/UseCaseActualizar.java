package co.com.Biblioteca.UseCase.Recurso;

import co.com.Biblioteca.Collections.Recurso;
import co.com.Biblioteca.Dto.RecursoDto;
import co.com.Biblioteca.Mapper.RecursoMapper;
import co.com.Biblioteca.Repository.RecursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Service
@Validated
public class UseCaseActualizar  implements Function<RecursoDto,Mono<Recurso>> {
    private final RecursoRepository repositorio;
    private final RecursoMapper mapper;

    @Autowired
    public UseCaseActualizar(RecursoMapper mapper, RecursoRepository repositorio) {
        this.repositorio = repositorio;
        this.mapper = mapper;
    }

    @Override
    public Mono<Recurso> apply(RecursoDto recursoDTO) {
        return repositorio.findById(recursoDTO.getId())
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND , "id erroneo")))
                .flatMap(recurso->repositorio.save(mapper.mapperToRecurso().apply(recursoDTO))).map(recurso->recurso);
    }
}