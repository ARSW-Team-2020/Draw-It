package edu.eci.arsw.drawit.services;


import edu.eci.arsw.drawit.model.Jugador;
import edu.eci.arsw.drawit.model.Sala;
import edu.eci.arsw.drawit.persistence.DrawitPersistence;
import edu.eci.arsw.drawit.persistence.DrawitPersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("DrawitServices")
public class DrawitServices {

    @Autowired
    @Qualifier("Action")
    DrawitPersistence drawitPersistence = null;

    public void addNewSala(Sala sala) throws DrawitPersistenceException {
        drawitPersistence.addSala(sala);
    }

    public void addJugadorToSala(Jugador jugador, Sala sala) throws DrawitPersistenceException {
        drawitPersistence.addJugadorToSala(jugador, sala);
    }

}
