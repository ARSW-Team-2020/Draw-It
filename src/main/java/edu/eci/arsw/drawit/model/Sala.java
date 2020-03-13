package edu.eci.arsw.drawit.model;

public class Sala {

    private char codigo;
    private int ronda;

    public Sala(){
        super();
    }

    public Sala(char codigo, int ronda){
        this.codigo=codigo;
        this.ronda=ronda;
    }

    public char getCodigo(){
        return codigo;
    }

    public int getRonda() {
        return ronda;
    }

    public void setCodigo(char codigo) {
        this.codigo = codigo;
    }

    public void setRonda(int ronda) {
        this.ronda = ronda;
    }
}
