package edu.eci.arsw.drawit.model;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Tablero {

    private int id;
    private List<Point> points=null;

    public Tablero(){
        
    }

    public Tablero(int id, List<Point> points ){
        this.id=id;
        this.points = points;
    }
   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

	public List<Point> getPoints() {
		return points;
	}

	public void setPoints(List<Point> points) {
		this.points = points;
	}

}
