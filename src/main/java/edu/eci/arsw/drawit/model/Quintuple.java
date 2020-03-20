package edu.eci.arsw.drawit.model;

import java.util.ArrayList;

public class Quintuple {

    private String autor;
    private String codigo;
    private ArrayList<Jugador> jugadores;
    private ArrayList<String> palabras;
    private Sala sala;

    public Quintuple(String autor,String codigo,ArrayList<Jugador> jugadores, ArrayList<String> palabras, Sala sala){
        this.autor=autor;
        this.codigo=codigo;
        this.jugadores=jugadores;
        this.palabras=palabras;
        this.sala=sala;
    }

    public String getAutor() {
        return autor;
    }

    public String getCodigo() {
        return codigo;
    }

    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }

    public ArrayList<String> getPalabras() {
        return palabras;
    }

    public Sala getSala() {
        return sala;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setJugadores(ArrayList<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public void setPalabras(ArrayList<String> palabras) {
        this.palabras = palabras;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }
}
