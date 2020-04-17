package edu.eci.arsw.drawit.model;

import java.util.ArrayList;
import java.util.Random;

public class Sala {

    private String codigo;
    private int ronda;
    private String autor;
    private ArrayList<Jugador> jugadores;
    private ArrayList<String> palabras;
    private ArrayList<Equipo> equipos;

    public Sala() {
        this.codigo = crearCodigo(8);
    }

    public Sala(Jugador autor) {
        this.autor = autor.getUsuario();
        this.codigo = crearCodigo(8);
        ronda = 0;
        jugadores = new ArrayList<Jugador>();
        jugadores.add(autor);
        equipos= new ArrayList<>();
        equipos.add(new Equipo("equipo1"));
        equipos.add(new Equipo("equipo2"));
    }

    public String getAutor() {
        return autor;
    }

    public String getCodigo() {
        return codigo;
    }

    public int getRonda() {
        return ronda;
    }

    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }

    public ArrayList<String> getPalabras() {
        return palabras;
    }

    public ArrayList<Equipo> getEquipos() {
        return equipos;
    }

    public void setAutor(String autor) {
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

    public void setPalabras(ArrayList<String> palabras) {
        this.palabras = palabras;
    }

    public void setEquipos(ArrayList<Equipo> equipos) {
        this.equipos = equipos;
    }

    public static String crearCodigo(int len) {


        // A strong password has Cap_chars, Lower_chars,
        // numeric value and symbols. So we are using all of
        // them to generate our password
        String Capital_chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String Small_chars = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";


        String values = Capital_chars + Small_chars +
                numbers;

        // Using random method
        Random rndm_method = new Random();

        char[] password = new char[len];

        for (int i = 0; i < len; i++) {
            // Use of charAt() method : to get character value
            // Use of nextInt() as it is scanning the value as int
            password[i] = values.charAt(rndm_method.nextInt(values.length()));

        }

        String codigo = String.valueOf(password);
        return codigo;
    }

    @Override
    public String toString() {
        return "Sala{" +
                "codigo='" + codigo + '\'' +
                ", ronda=" + ronda +
                ", autor=" + autor +
                ", jugadores=" + jugadores +
                '}';
    }

}
