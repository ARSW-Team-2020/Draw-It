package edu.eci.arsw.drawit.model;

public class Jugador {

    private String usuario;
    private int puntaje;


    public Jugador() {
    }

    public Jugador(String usuario, int puntaje) {
        this.usuario = usuario;
        this.puntaje = puntaje;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    @Override
    public String toString() {
        return "Jugador{" +
                "usuario='" + usuario + '\'' +
                ", puntaje=" + puntaje +
                '}';
    }
}
