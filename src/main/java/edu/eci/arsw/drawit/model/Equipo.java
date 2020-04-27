package edu.eci.arsw.drawit.model;

import java.util.ArrayList;

public class Equipo {

    private String nombre;
    private int rondasGanadas;
    private String[] jugadores = new String[4];
    private ArrayList<Jugador> players;

    private Tablero tablero;
    private int turno = 0;
    public Equipo() {

        super();
    }

    public Equipo(String nombre) {
        this.nombre = nombre;
        this.rondasGanadas = 0;
        this.tablero = new Tablero();
        this.players =  new ArrayList<>();
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

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setRondasGanadas(int rondasGanadas) {
        this.rondasGanadas = rondasGanadas;
    }

    /**
     *  Apartir de los nombres de los jugadores, creamos los jugadores
     *  y los guardamos en un arraylist player
     * @param jugadores
     */
    public void setJugadores(String[] jugadores) {
        this.jugadores = jugadores;
        // creamos los jugadores apartir de los nombres para tener los puntajes
        players.clear();
        for(String namePlayer: jugadores){
            System.out.println(namePlayer);
            Jugador player = new Jugador(namePlayer);
            players.add(player);
        }
        System.out.println("Equipos creados");
        nextTurno();
    }


    /**
     * obtener el pintor de turno
     * @return Jugador pintor del tablero
     */
    public Jugador getPainterTurno(){
        return tablero.getPintor();
    }

    /**
     * seleccionar el nuevo pintor de turno
     * @param painter
     */
    public void setPainterTurno(Jugador painter) {
        tablero.setPintor(painter);
    }

    /**
     *  obtener la palabra de turno
     * @return Palabra
     */
    public Palabra getPalabraTurno(){
        return tablero.getPalabra();
    }

    public void nextTurno(){
        System.out.println(turno%4+" "+ players.size());
        setPainterTurno(players.get( (turno%4)));
        tablero.cambiarPalabra();
        turno++;
        System.out.println("Round # "+turno);
    }
}
