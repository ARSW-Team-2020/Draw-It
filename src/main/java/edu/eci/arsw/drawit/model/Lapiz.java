package edu.eci.arsw.drawit.model;

public class Lapiz {

    private int id;
    private char color;
    private char tipo;
    private char tamaño;

    public Lapiz(){
        super();
    }

    public Lapiz(int id, char color, char tipo, char tamaño){
        this.id=id;
        this.color=color;
        this.tipo=tipo;
        this.tamaño=tamaño;
    }

    public int getId() {
        return id;
    }

    public char getColor() {
        return color;
    }

    public char getTipo() {
        return tipo;
    }

    public char getTamaño() {
        return tamaño;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setColor(char color) {
        this.color = color;
    }

    public void setTipo(char tipo) {
        this.tipo = tipo;
    }

    public void setTamaño(char tamaño) {
        this.tamaño = tamaño;
    }
}
