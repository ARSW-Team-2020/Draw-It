package edu.eci.arsw.drawit.persistence.impl;


import edu.eci.arsw.drawit.model.Equipo;
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

    private CopyOnWriteArrayList<Equipo> equipos= new CopyOnWriteArrayList<>();

    public ActionsDrawitPersistence() {
        Jugador carlos = new Jugador("Carlos");
        Jugador yeisson= new Jugador("Yeisson");
        Jugador javier= new Jugador("Javier");
        Jugador oscar= new Jugador("Oscar");
        Jugador daniel= new Jugador("Daniel");
        Jugador juan= new Jugador("Juan");
        Jugador sebastian= new Jugador("Sebastian");
        Sala jupiter= new Sala(carlos);
        jupiter.getJugadores().add(yeisson);
        jupiter.getJugadores().add(javier);
        jupiter.getJugadores().add(oscar);
        jupiter.getJugadores().add(daniel);
        jupiter.getJugadores().add(juan);
        jupiter.getJugadores().add(sebastian);
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

    @Override
    public String[] getEquiposBySalaAndAuthor(String codigo,String usuario) throws DrawItException {
        String[] jugadores = new String[4];
        for (int i = 0; i < equipos.size(); i++) {
            if (equipos.get(i).getSala().getCodigo().equals(codigo)) {
                for (int j = 0; j < equipos.get(i).getJugadores().length; j++) {
                    if (equipos.get(i).getJugadores()[j].equals(usuario)) {
                        jugadores = equipos.get(i).getJugadores();
                        return jugadores;
                    }
                }
            }
        }

        return crearEquipo(codigo,usuario);
    }

     public String[] crearEquipo(String codigo,String usuario) throws DrawItException {
        boolean ex=true;
        boolean estaE1=false;
        String[] lista1= new String[4];
        String[] lista2= new String[4];
        for (int i=0;i<salas.size();i++) {
            if (salas.get(i).getCodigo().equals(codigo)) {
                Equipo equipo1= new Equipo("1", salas.get(i).getSala());
                Equipo equipo2= new Equipo("2", salas.get(i).getSala());
                for(int j=0; j<4;j++){
                    if(salas.get(i).getJugadores().get(j).getUsuario().equals(usuario)){
                        estaE1=true;
                    }
                    lista1[j]=salas.get(i).getJugadores().get(j).getUsuario();
                    lista2[j]=salas.get(i).getJugadores().get(j+4).getUsuario();
                }
                equipo1.setJugadores(lista1);
                equipo2.setJugadores(lista2);
                equipos.add(equipo1);
                equipos.add(equipo2);
                ex=false;
                break;
            }
        }
        if(ex){throw new DrawItException("La sala no existe");}
        else {
            if (estaE1) {
                return lista1;
            } else {
                return lista2;
            }
        }
    }


}

