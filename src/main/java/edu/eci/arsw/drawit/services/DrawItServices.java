package edu.eci.arsw.drawit.services;


import edu.eci.arsw.drawit.model.Jugador;
import edu.eci.arsw.drawit.model.Sala;
import edu.eci.arsw.drawit.persistence.DrawItException;
import edu.eci.arsw.drawit.persistence.DrawitPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("drawItServices")
public class DrawItServices {

    @Autowired
    @Qualifier("Action")
    DrawitPersistence drawitPersistence = null;

    public void addNewSala(Sala sala) throws DrawItException {
        drawitPersistence.addSala(sala);
    }

    public void addJugadorToSala(Jugador jugador, Sala sala) throws DrawItException {
        drawitPersistence.addJugadorToSala(jugador, sala);
    }

    public String getCodigoUnicoDeLaSala() throws DrawItException {

        return drawitPersistence.getCodigoUnicoDeLaSala();
    }


}
