package edu.eci.arsw.drawit.model;

import java.util.List;

public class Equipo {

    private String nombre;
    private int rondasGanadas;
    private String[] jugadores = new String[4];
    private Sala sala;

    public Equipo() {
        super();
    }

    public Equipo(String nombre, Sala sala) {
        this.nombre = nombre;
        this.rondasGanadas = 0;
        this.sala=sala;
    }

    public String getNombre() {
        return nombre;
    }

    public int getRondasGanadas() {
        return rondasGanadas;
    }

    public String[] getJugadores() {
        return jugadores;
    }

    public Sala getSala() {
        return sala;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setRondasGanadas(int rondasGanadas) {
        this.rondasGanadas = rondasGanadas;
    }

    public void setJugadores(String[] jugadores) {
        this.jugadores = jugadores;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }
}
