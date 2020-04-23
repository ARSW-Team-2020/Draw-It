package edu.eci.arsw.drawit.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Ronda {
    private String palabra;
    private String fecha_fin;
    private ArrayList<String> palabras = new ArrayList<String>(Arrays.asList("Koala","Conejo","Mono","Mantarraya","Elefante", "Caballo",
            "Cerdo","Mariposa","Cabra","Lobo","Dentista","Carnicero","Escritor","Cocinera","Cirujano",
            "Mecánico","Profesora","Arquitecto","Granjero","Bombero","Agricultor","Espalda","Mente",
            "Piel","Boca","Trampa","Terraza","Bolsa","Frio","Asteroide","Arruga","Picante"));


    public Ronda(){

    }

    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }

    public String getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(String fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public void cambiarPalabra(){
        Random rand = new Random();
        palabra = palabras.get(rand.nextInt(palabras.size()));
    }
}
