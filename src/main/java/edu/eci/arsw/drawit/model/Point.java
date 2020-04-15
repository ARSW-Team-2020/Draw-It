package edu.eci.arsw.drawit.model;

public class Point {
	   
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point() {
    }    
    
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    /**
     * Metodo encargado de validar si el punto pasado por parametro es igual a el punto actual
     * @param puntoComparar
     * @return boolean <br>
     * <b>True</b>: Si los puntos son iguales<br>
     * <b>False</b>: SI los puntos son diferetnes
     */
    public boolean sonPuntosIgual(Point puntoComparar){
        return puntoComparar.getX() == this.x && puntoComparar.getY() == this.y;
    }
    
}