package edu.eci.arsw.drawit.persistence.impl;


import edu.eci.arsw.drawit.model.Jugador;
import edu.eci.arsw.drawit.model.Quintuple;
import edu.eci.arsw.drawit.model.Sala;
import edu.eci.arsw.drawit.persistence.DrawItException;
import edu.eci.arsw.drawit.persistence.DrawitPersistence;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

@Service("Action")
public class ActionsDrawitPersistence implements DrawitPersistence {

    private Map<Map<Tuple<Jugador, String>, ArrayList<Jugador>>, Sala> sala = new ConcurrentHashMap<>();
    private Map<Tuple<Jugador, String>, ArrayList<Jugador>> salaIni = new ConcurrentHashMap<>();

    private  Map<String,Jugador> jugador= new ConcurrentHashMap<>();

    private CopyOnWriteArrayList<Quintuple> salas= new CopyOnWriteArrayList<>();

    public ActionsDrawitPersistence() {
        Jugador carlos = new Jugador("Carlos");
        Sala jupiter= new Sala(carlos);
        salas.add(new Quintuple(jupiter.getAutor(),jupiter.getCodigo(),jupiter.getJugadores(),jupiter.getPalabras(),jupiter));
    }

    @Override
    public void addSala(Sala sl) throws DrawItException {
        for (int i=0; i<salas.size();i++){
            if(salas.get(i).getCodigo().equals(sl.getCodigo())){
                throw new DrawItException("La sala " + sl + "ya existe");
            }
        }
        salas.add(new Quintuple(sl.getAutor(),sl.getCodigo(),sl.getJugadores(),sl.getPalabras(),sl));
    }

    @Override
    public ArrayList<Sala> getSalas() throws DrawItException{
        ArrayList<Sala> listaSalas=new ArrayList<>();
        if(salas.size()==0){throw new DrawItException("No hay salas creadas");}
        for (int i=0; i<salas.size();i++){
            listaSalas.add(salas.get(i).getSala());
        }
        return listaSalas;
    }

    @Override
    public void addJugador(Jugador jg) throws DrawItException {
        if (jugador.containsKey(jg.getUsuario())) {
            throw new DrawItException("el jugador " + jg + "ya existe");
        } else {
            jugador.put(jg.getUsuario(),jg);
        }
    }


    @Override
    public void addJugadorToSala(Jugador jg, String codigo) throws DrawItException {
        boolean ex=true;
        for(int i=0; i<salas.size();i++){
            if(salas.get(i).getCodigo().equals(codigo)){
                if(!salas.get(i).getJugadores().contains(jg)){
                    salas.get(i).getJugadores().add(jg);
                    ex=false;
                }
                break;
            }
        }
        if(ex){throw new DrawItException("La sala no existe o ya hay un jugador con ese nombre");}

    }



}

