package co.com.Biblioteca.Collections;

import co.com.Biblioteca.Enums.AreaTematica;
import co.com.Biblioteca.Enums.TipoRecurso;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Date;

@Document("Recurso")
public class Recurso {

    @Id
    private String id;
    private String nombre;
    private boolean disponible= true;
    private Date fechaPrestamo;
    private AreaTematica areaTematica;
    private TipoRecurso tipoRecurso;

    public Recurso() {
    }

    public Recurso(String nombre, AreaTematica areaTematica, TipoRecurso tipoRecurso) {
        this.nombre = nombre;
        this.areaTematica = areaTematica;
        this.tipoRecurso = tipoRecurso;
    }

    public Recurso(String id, String nombre, AreaTematica areaTematica, TipoRecurso tipoRecurso, Boolean Disponible) {
        this.id=id;
        this.nombre = nombre;
        this.areaTematica = areaTematica;
        this.tipoRecurso = tipoRecurso;
        this.disponible=Disponible;
    }

    public Recurso(String id, String nombre, AreaTematica areaTematica, TipoRecurso tipoRecurso, Boolean Disponible,Date fechaPrestamo) {
        this.id=id;
        this.nombre = nombre;
        this.areaTematica = areaTematica;
        this.tipoRecurso = tipoRecurso;
        this.disponible=Disponible;
        this.fechaPrestamo=fechaPrestamo;
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

    public void setFechaPrestamo(Date fechaPrestamo) {
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




