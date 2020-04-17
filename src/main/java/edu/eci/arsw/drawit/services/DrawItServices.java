package edu.eci.arsw.drawit.services;


import edu.eci.arsw.drawit.model.Jugador;
import edu.eci.arsw.drawit.model.Sala;
import edu.eci.arsw.drawit.persistence.DrawItException;
import edu.eci.arsw.drawit.persistence.DrawitPersistence;
import edu.eci.arsw.drawit.repository.JugadorRepository;
import edu.eci.arsw.drawit.repository.SalaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service("drawItServices")
public class DrawItServices {

    @Autowired
    @Qualifier("jugador")
    JugadorRepository jugadorRepository;

    @Autowired
    @Qualifier("sala")
    SalaRepository salaRepository;


    @Autowired
    @Qualifier("Action")
    DrawitPersistence drawitPersistence = null;

    public void addNewSala(Sala sala) throws DrawItException {
        System.out.println(sala);
        drawitPersistence.addSala(sala);
    }

    public ArrayList<String> getSalaCreada (Sala sala) throws DrawItException {
        return drawitPersistence.getSalaCreada(sala);
    }

    public ArrayList<Sala> getSalas() throws DrawItException{
        return drawitPersistence.getSalas();
    }

    public void addJugadorToSala(Jugador jugador, String codigo) throws DrawItException {
        drawitPersistence.addJugadorToSala(jugador, codigo);
    }

    public ArrayList<String> getJugadoresBySala (String codigo) throws DrawItException{
        return drawitPersistence.getJugadoresBySala(codigo);
    }

    public String[] getEquiposBySalaAndAuthor (String codigo, String autor) throws DrawItException{
        return drawitPersistence.getEquiposBySalaAndAuthor(codigo,autor);
    }





}
