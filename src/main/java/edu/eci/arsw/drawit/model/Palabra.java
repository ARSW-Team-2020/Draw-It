package edu.eci.arsw.drawit.model;

public class Palabra {

    private char palabra;

    public Palabra(){
        super();
    }

    public Palabra (char palabra){
        this.palabra=palabra;
    }

    public char getPalabra() {
        return palabra;
    }

    public void setPalabra(char palabra) {
        this.palabra = palabra;
    }
}
