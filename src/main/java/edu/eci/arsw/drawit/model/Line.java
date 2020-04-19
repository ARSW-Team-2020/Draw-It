package edu.eci.arsw.drawit.model;

public class Line {

    private int x1;
    private int y1;
    private int x2;
    private int y2;
    private String color;
    private int grosor;

    public Line(int x1, int y1, int x2, int y2, String color, int grosor) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.color = color;
        this.grosor = grosor;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getGrosor() {
        return grosor;
    }

    public void setGrosor(int grosor) {
        this.grosor = grosor;
    }

    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public int getY1() {
        return y1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public int getX2() {
        return x2;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public int getY2() {
        return y2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }

    @Override
    public String toString() {
        return this.x1 + " " + this.y1 +" "+this.x2+ " "+this.y2 + " "+this.color+" "+this.grosor;
    }
}