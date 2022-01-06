
package co.com.Biblioteca.UseCase.Recurso;


import co.com.Biblioteca.Dto.RecursoDto;


import co.com.Biblioteca.Mapper.RecursoMapper;
import co.com.Biblioteca.Repository.RecursoRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;

import java.util.function.Supplier;

@Service
@Validated
public class UseCaseMostrarRecursos implements Supplier<Flux<RecursoDto>> {

    private final RecursoRepository Repository;
    private final RecursoMapper mapper;

    public UseCaseMostrarRecursos(RecursoRepository repository, RecursoMapper mapper) {
        this.Repository = repository;
        this.mapper = mapper;
    }


    @Override
    public Flux<RecursoDto> get() {
        return Repository.findAll().map(mapper.mapRecursoToDTO());
    }
}


