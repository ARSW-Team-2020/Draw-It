package edu.eci.arsw.drawit.persistence;

public class DrawItException extends Exception {

    public DrawItException(String message) {
        super(message);
    }

    public DrawItException(String message, Throwable cause) {
        super(message, cause);
    }
}