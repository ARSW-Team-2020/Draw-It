package edu.eci.arsw.drawit.persistence.impl;


import edu.eci.arsw.drawit.model.Jugador;
import edu.eci.arsw.drawit.model.Sala;
import edu.eci.arsw.drawit.persistence.DrawItException;
import edu.eci.arsw.drawit.persistence.DrawitPersistence;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service("Action")
public class ActionsDrawitPersistence implements DrawitPersistence {

    private ConcurrentHashMap<String,Sala> salas1= new ConcurrentHashMap<>();

    private List<String>  palabras = new ArrayList<>(Arrays.asList("Koala","Conejo","Mono","Mantarraya","Elefante", "Caballo",
            "Cerdo","Mariposa","Cabra","Lobo","Dentista","Carnicero","Escritor","Cocinera","Cirujano",
            "Mecánico","Profesora","Arquitecto","Granjero","Bombero","Agricultor","Espalda","Mente",
            "Piel","Boca","Trampa","Terraza","Bolsa","Frio","Asteroide","Arruga","Picante"));

    public ActionsDrawitPersistence() {
        Jugador carlos = new Jugador("Carlos");
        Jugador yeisson= new Jugador("Yeisson");
        Jugador javier= new Jugador("Javier");
        Jugador oscar= new Jugador("Oscar");
        Jugador daniel= new Jugador("Daniel");
        Jugador sebastian= new Jugador("Sebastian");
        Sala jupiter= new Sala(carlos);
        jupiter.getJugadores().add(yeisson);
        jupiter.getJugadores().add(javier);
        jupiter.getJugadores().add(oscar);
        jupiter.getJugadores().add(daniel);
        jupiter.getJugadores().add(sebastian);
        salas1.put(jupiter.getCodigo(),jupiter);
    }

    private boolean hashIterator(String codigo){
        // Get the iterator over the HashMap
        Iterator<Map.Entry<String, Sala>> iterator = salas1.entrySet().iterator();
        // flag to store result
        boolean isKeyPresent = false;
        // Iterate over the HashMap
        while (iterator.hasNext()) {
            // Get the entry at this iteration
            Map.Entry<String, Sala> entry = iterator.next();
            // Check if this key is the required key
            if (codigo.equals(entry.getKey())) {
                isKeyPresent = true;
            }
        }
        return isKeyPresent;
    }

    @Override
    public void addSala(Sala sl) throws DrawItException {
        if(hashIterator(sl.getCodigo())) {
            throw new DrawItException("La sala " + sl + "ya existe");
        }else{
            salas1.put(sl.getCodigo(), sl);
        }
    }

    @Override
    public ArrayList<String> getSalaCreada (Sala sl) throws DrawItException{
        ArrayList<String> lista= new ArrayList<>();
        if(hashIterator(sl.getCodigo())) {
            throw new DrawItException("La sala " + sl + "ya existe");
        }else{
            salas1.put(sl.getCodigo(), sl);
            lista.add(sl.getCodigo());
            lista.add(sl.getAutor());
            System.out.println(lista);
            return lista;
        }
    }

    @Override
    public ArrayList<Sala> getSalas() throws DrawItException{
        ArrayList<Sala> listaSalas=new ArrayList<>();
        if(salas1.isEmpty()){throw new DrawItException("No hay salas creadas");}
        Iterator<Map.Entry<String, Sala>> iterator = salas1.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Sala> entry = iterator.next();
            listaSalas.add(entry.getValue());
        }

        return listaSalas;
    }

    @Override
    public void addJugadorToSala(Jugador jg, String codigo) throws DrawItException {
        boolean ex=true;
        boolean esta=false;
        if(hashIterator(codigo)){
            Sala salaI;
            salaI = salas1.get(codigo);
            for(int i=0; i<salaI.getJugadores().size();i++) {
                if (salaI.getJugadores().get(i).getUsuario().equals(jg.getUsuario())) {
                    esta = true;
                    break;
                }
            }
            if(!esta && salaI.getJugadores().size()<8){
                salaI.getJugadores().add(jg);
                ex=false;
            }
            System.out.println(salaI.getJugadores());
        }
        if(ex){throw new DrawItException("La sala no existe o ya hay un jugador con ese nombre");}
    }

    @Override
    public ArrayList<String> getJugadoresBySala(String codigo) throws DrawItException{
        ArrayList<String> listaJugadores=new ArrayList<>();
        boolean ex=true;
        if(hashIterator(codigo)){
            Sala salaI;
            salaI = salas1.get(codigo);
            for(int i=0;i<salaI.getJugadores().size();i++){
                listaJugadores.add(salaI.getJugadores().get(i).getUsuario());
            }
            System.out.println(listaJugadores);
            return listaJugadores;
        }else{
            throw new DrawItException("La sala no existe o ya hay un jugador con ese nombre");
        }
    }

    @Override
    public String[] getEquiposBySalaAndAuthor(String codigo,String usuario) throws DrawItException {
        if(hashIterator(codigo)){
            Sala salaI;
            salaI = salas1.get(codigo);
            if(salaI.getEquipos().get(0).getJugadores()[0]!= null){
                return returnEquipo(salaI,usuario);

            }else{
                for(int i=0; i<4; i++){
                    salaI.getEquipos().get(0).getJugadores()[i]=salaI.getJugadores().get(i).getUsuario();
                    salaI.getEquipos().get(1).getJugadores()[i]=salaI.getJugadores().get(i+4).getUsuario();
                }
                return returnEquipo(salaI,usuario);
            }
        }else{
            throw new DrawItException("La sala no existe");
        }
    }

    private String[] returnEquipo(Sala salaI, String usuario){
        String[] equipo = null;
        String[] equipo2 = null;
        String team="";
        for(int i=0; i<salaI.getEquipos().get(0).getJugadores().length; i++){
            if(salaI.getEquipos().get(0).getJugadores()[i].equals(usuario)){
                equipo = salaI.getEquipos().get(0).getJugadores();
                equipo2 = salaI.getEquipos().get(1).getJugadores();
                team = "0";
            }
        }
        if (!team.contains("0")){
            equipo = salaI.getEquipos().get(1).getJugadores();
            equipo2 = salaI.getEquipos().get(0).getJugadores();
            team = "1";
        }
        String[] nuevo = new String[9];
        for (int i = 1;i < 5;i++){
            nuevo[i] = equipo[i-1];
            nuevo[i+4] = equipo2[i-1];
        }
        nuevo[0] = team;
        return nuevo;
    }

    public String getPalabra() {
        Random rand = new Random();
        return palabras.get(rand.nextInt(palabras.size()));
    }

}