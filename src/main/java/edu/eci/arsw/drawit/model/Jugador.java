package edu.eci.arsw.drawit.model;

public class Jugador {

    private int userName;
    private int puntaje;
    private char correo;

    public Jugador(){
        super();
    }

    public Jugador(int UserName, int puntaje, char correo){
        this.userName=userName;
        this.puntaje=puntaje;
        this.correo=correo;
    }

    public int getUserName() {
        return userName;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public char getCorreo() {
        return correo;
    }

    public void setUserName(int userName) {
        this.userName = userName;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    public void setCorreo(char correo) {
        this.correo = correo;
    }
}
