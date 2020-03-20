package edu.eci.arsw.drawit.model;

import java.util.List;

public class Equipo {

    private char nombre;
    private int rondasGanadas;
    private Jugador[] jugadores = new Jugador[4];

    public Equipo() {
        super();
    }

    public Equipo(char nombre, int rondasGanadas, Jugador[] jugadores) {
        this.nombre = nombre;
        this.rondasGanadas = rondasGanadas;
        //(this.jugadores = jugadores;
    }

    public char getNombre() {
        return nombre;
    }

    public int getRondasGanadas() {
        return rondasGanadas;
    }

    public Jugador[] getJugadores() {
        return jugadores;
    }

    public void setNombre(char nombre) {
        this.nombre = nombre;
    }

    public void setRondasGanadas(int rondasGanadas) {
        this.rondasGanadas = rondasGanadas;
    }
}
