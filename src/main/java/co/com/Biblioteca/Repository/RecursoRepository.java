package co.com.Biblioteca.Repository;

import co.com.Biblioteca.Collections.Recurso;
import co.com.Biblioteca.Enums.AreaTematica;
import co.com.Biblioteca.Enums.TipoRecurso;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface RecursoRepository extends ReactiveMongoRepository<Recurso,String> {
    Flux<Recurso> findByAreaTematica(AreaTematica area);
    Flux<Recurso> findByTipoRecurso(TipoRecurso tipo);
    Flux<Recurso> findByAreaTematicaAndTipoRecurso(AreaTematica area, TipoRecurso tipo);
    Flux<Recurso> findByAreaTematicaOrTipoRecurso(AreaTematica area, TipoRecurso tipo);
}
