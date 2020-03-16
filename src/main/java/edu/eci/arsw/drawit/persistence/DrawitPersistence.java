package edu.eci.arsw.drawit.persistence;

import edu.eci.arsw.drawit.model.Jugador;
import edu.eci.arsw.drawit.model.Sala;
import org.springframework.stereotype.Service;

@Service
public interface DrawitPersistence {

    public void addSala(Sala sl) throws DrawItException;

    public void addJugadorToSala(Jugador jg, Sala sl) throws DrawItException;

    public String getCodigoUnicoDeLaSala() throws  DrawItException;


}
