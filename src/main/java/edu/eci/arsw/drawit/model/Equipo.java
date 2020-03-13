package edu.eci.arsw.drawit.model;

import java.util.List;

public class Equipo {

    private char nombre;
    private int rondasGanadas;
    private List jugadores;

    public Equipo(){
        super();
    }

    public Equipo(char nombre, int rondasGanadas){
        this.nombre=nombre;
        this.rondasGanadas=rondasGanadas;
    }

    public char getNombre() {
        return nombre;
    }

    public int getRondasGanadas() {
        return rondasGanadas;
    }

    public void setNombre(char nombre) {
        this.nombre = nombre;
    }

    public void setRondasGanadas(int rondasGanadas) {
        this.rondasGanadas = rondasGanadas;
    }
}
