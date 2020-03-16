package edu.eci.arsw.drawit.model;

import java.util.ArrayList;
import java.util.Random;

public class Sala {

    private String codigo;
    private int ronda;
    private Jugador autor;
    private ArrayList<Jugador> jugadores;

    public Sala() {
        this.codigo = crearCodigo(8);
    }

    public Sala(Jugador autor) {
        this.autor = autor;
        this.codigo = crearCodigo(8);
        ronda = 0;
        jugadores = new ArrayList<Jugador>();
        jugadores.add(autor);
    }

    public Jugador getAutor() {
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

    public static String crearCodigo(int len) {
        System.out.println("Generating password using random() : ");
        System.out.print("Your new password is : ");

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
            password[i] =
                    values.charAt(rndm_method.nextInt(values.length()));

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

    public static void main(String[] args) {
        Jugador carlos = new Jugador("Carlos", 4);
        Sala sala1 = new Sala(carlos);

        System.out.println(sala1);

    }
}
