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
    public ArrayList<String> getSalaCreada (Sala sl) throws DrawItException{
        ArrayList<String> lista= new ArrayList<>();
        for (int i=0; i<salas.size();i++){
            if(salas.get(i).getCodigo().equals(sl.getCodigo())){
                throw new DrawItException("La sala " + sl + "ya existe");
            }
        }
        salas.add(new Quintuple(sl.getAutor(),sl.getCodigo(),sl.getJugadores(),sl.getPalabras(),sl));
        lista.add(sl.getCodigo());
        lista.add(sl.getAutor());
        return lista;
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
    public void addJugadorToSala(Jugador jg, String codigo) throws DrawItException {
        boolean ex=true;
        boolean esta=false;
        for(int i=0; i<salas.size();i++){
            if(salas.get(i).getCodigo().equals(codigo)){
                for(int j=0; j<salas.get(i).getJugadores().size(); j++){
                    if(salas.get(i).getJugadores().get(j).getUsuario().equals(jg.getUsuario())){
                        esta=true;
                        break;
                    }
                }
                if(!esta && salas.get(i).getJugadores().size()<8){
                    salas.get(i).getJugadores().add(jg);
                    ex=false;
                }
                System.out.println(salas.get(i).getJugadores());
                break;
            }
        }

        if(ex){throw new DrawItException("La sala no existe o ya hay un jugador con ese nombre");}

    }

    @Override
    public ArrayList<String> getJugadoresBySala(String codigo) throws DrawItException{

        ArrayList<String> listaJugadores=new ArrayList<>();
        boolean ex=true;
        for (int i=0;i<salas.size();i++){
            if(salas.get(i).getCodigo().equals(codigo)){
                for(int j=0; j<salas.get(i).getJugadores().size();j++){
                    listaJugadores.add(salas.get(i).getJugadores().get(j).getUsuario());
                }
                ex=false;
                break;
            }
        }
        System.out.println(listaJugadores);
        if(ex){throw new DrawItException("La sala no existe o ya hay un jugador con ese nombre");}
        else{ return listaJugadores;}

    }



}

