package co.com.Biblioteca.Utils;

import java.util.Date;

public class Mensaje {

        private boolean estado;
        private String mensaje;

    public Mensaje() {

    }
        public Mensaje(boolean estado, String mensaje) {
            this.estado = estado;
            this.mensaje = mensaje;
        }


    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Mensaje printAvailability(Boolean disponible, Date fechaPrestamo) {
        return disponible ?
                new Mensaje(true,"El recurso esta disponible"):
                new Mensaje(false,"El recurso fue prestado el: "+fechaPrestamo);
    }

    public Mensaje printLoan(Boolean disponible, Date fechaPrestamo) {
        return disponible ?
                new Mensaje(true,"El recurso estaba disponible, y se te fue prestado el: "+fechaPrestamo):
                new Mensaje(false,  "El recurso ya esta prestado");

    }

    public Mensaje printBack(Boolean disponible , Date fechaPrestamo){
        return !disponible ?
        new Mensaje(true,"El recurso fue entregado con exito"):
        new Mensaje(false,("El recurso no esta prestado" ));
    }
}
