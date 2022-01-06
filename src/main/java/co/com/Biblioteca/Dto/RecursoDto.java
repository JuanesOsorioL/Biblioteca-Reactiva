package co.com.Biblioteca.Dto;

import co.com.Biblioteca.Enums.AreaTematica;
import co.com.Biblioteca.Enums.TipoRecurso;

import java.time.LocalDate;
import java.util.Date;

public class RecursoDto {

    private String id;
    private String nombre;
    private boolean disponible;
    private Date fechaPrestamo;
    private AreaTematica areaTematica;
    private TipoRecurso tipoRecurso;

    public RecursoDto() {
    }

    public RecursoDto(String nombre, AreaTematica areaTematica, TipoRecurso tipoRecurso) {
        this.nombre = nombre;
        this.areaTematica = areaTematica;
        this.tipoRecurso = tipoRecurso;
    }

    public RecursoDto(String id, String nombre, boolean disponible, Date  fechaPrestamo, AreaTematica areaTematica, TipoRecurso tipoRecurso) {
        this.id = id;
        this.nombre = nombre;
        this.disponible = disponible;
        this.fechaPrestamo = fechaPrestamo;
        this.areaTematica = areaTematica;
        this.tipoRecurso = tipoRecurso;
    }

    public RecursoDto(String id, TipoRecurso tipoRecurso, boolean disponible, AreaTematica areaTematica, String nombre, Date  fechaPrestamo) {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public Date getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(Date  fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public AreaTematica getAreaTematica() {
        return areaTematica;
    }

    public void setAreaTematica(AreaTematica areaTematica) {
        this.areaTematica = areaTematica;
    }

    public TipoRecurso getTipoRecurso() {
        return tipoRecurso;
    }

    public void setTipoRecurso(TipoRecurso tipoRecurso) {
        this.tipoRecurso = tipoRecurso;
    }
}
