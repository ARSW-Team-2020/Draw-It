package edu.eci.arsw.drawit.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Tablero {
    private Jugador pintor;
    private Palabra palabra;
    private ArrayList<String> palabras = new ArrayList<String>(Arrays.asList("Koala","Conejo","Mono","Mantarraya","Elefante", "Caballo",
            "Cerdo","Mariposa","Cabra","Lobo","Dentista","Lapiz","Reloj","Dado","Ojo",
            "Pizza","Flor","Gafas","Escalera","Estrella","Fresa","Sol","Camara",
            "Gato","Boca","Iglesia","Delfin","Nariz","Frio","Huevo","Carro","Arbol"));

    public Tablero(){

        super();
        this.palabra= new Palabra();
    }
    public Tablero(Jugador player, Palabra palabra){
        this.pintor = player;
        this.palabra = palabra;
    }


    public Palabra getPalabra() {
        return this.palabra;
    }

    public void setPalabra(Palabra palabra) {
        this.palabra = palabra;
    }

    public Jugador getPintor() {
        return this.pintor;
    }

    public void setPintor(Jugador pintor) {
        this.pintor = pintor;
    }
    public void cambiarPalabra(){
        Random rand = new Random();
        palabra.setPalabra( palabras.get(rand.nextInt(palabras.size())));
    }
}
