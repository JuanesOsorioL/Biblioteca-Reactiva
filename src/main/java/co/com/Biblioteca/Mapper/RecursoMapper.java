package co.com.Biblioteca.Mapper;

import co.com.Biblioteca.Dto.RecursoDto;
import co.com.Biblioteca.Collections.Recurso;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

@Component
public class RecursoMapper {

    public Function<RecursoDto, Recurso> mapperToRecurso() {
        return updateRecurso -> {
            Recurso recurso = new Recurso();
            recurso.setId(updateRecurso.getId());
            recurso.setAreaTematica(updateRecurso.getAreaTematica());
            recurso.setDisponible(updateRecurso.isDisponible());
            recurso.setFechaPrestamo(updateRecurso.getFechaPrestamo());
            recurso.setNombre(updateRecurso.getNombre());
            recurso.setTipoRecurso(updateRecurso.getTipoRecurso());
            return recurso;
        };
    }


    public Function<Recurso, RecursoDto> mapRecursoToDTO() {
        return recursoDTONew ->{
            RecursoDto recursoDTO=new RecursoDto();
            recursoDTO.setId(recursoDTONew.getId());
            recursoDTO.setAreaTematica(recursoDTONew.getAreaTematica());
            recursoDTO.setDisponible(recursoDTONew.isDisponible());
            recursoDTO.setFechaPrestamo(recursoDTONew.getFechaPrestamo());
            recursoDTO.setNombre(recursoDTONew.getNombre());
            recursoDTO.setTipoRecurso(recursoDTONew.getTipoRecurso());
            return recursoDTO;

        };
    }
    ////////////////
    public Recurso fromDTO(RecursoDto dto) {
        Recurso recurso = new Recurso();
        recurso.setId(dto.getId());
        recurso.setNombre(dto.getNombre());
        recurso.setDisponible(dto.isDisponible());
        recurso.setFechaPrestamo(dto.getFechaPrestamo());
        recurso.setAreaTematica(dto.getAreaTematica());
        recurso.setAreaTematica(dto.getAreaTematica()
        );
        return recurso;
    }

    public RecursoDto fromCollection(Recurso collection) {
        RecursoDto recursoDto = new RecursoDto();
        recursoDto.setId(collection.getId());
        recursoDto.setNombre(collection.getNombre());
        recursoDto.setDisponible(collection.isDisponible());
        recursoDto.setFechaPrestamo(collection.getFechaPrestamo());
        recursoDto.setAreaTematica(collection.getAreaTematica());
        recursoDto.setTipoRecurso(collection.getTipoRecurso());
        return recursoDto;
    }


    public List<RecursoDto> fromCollectionList(List<Recurso> collection){
        if(collection==null){
            return null;
        }
        List<RecursoDto> list = new ArrayList<>(collection.size());
        Iterator listTrack = collection.iterator();

        while(listTrack.hasNext()){
            Recurso recurso = (Recurso) listTrack.next();
            list.add(fromCollection(recurso));
        }
        return list;
    }
}
