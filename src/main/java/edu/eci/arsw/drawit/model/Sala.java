package edu.eci.arsw.drawit.model;

import java.util.ArrayList;

public class Sala {

    private String codigo;
    private int ronda;
    private Jugador autor;
    private ArrayList<Jugador> jugadores;

    public Sala(){
        super();
    }

    public Sala(Jugador autor, String codigo){
        this.autor=autor;
        this.codigo=codigo;
        ronda=0;
        jugadores=new ArrayList<Jugador>();
        jugadores.add(autor);
    }

    public Jugador getAutor() {
        return autor;
    }

    public String getCodigo(){
        return codigo;
    }

    public int getRonda() {
        return ronda;
    }

    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }

    public void setAutor(Jugador autor) {
        this.autor = autor;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setRonda(int ronda) {
        this.ronda = ronda;
    }

    public void setJugadores(ArrayList<Jugador> jugadores) {
        this.jugadores = jugadores;
    }
}
