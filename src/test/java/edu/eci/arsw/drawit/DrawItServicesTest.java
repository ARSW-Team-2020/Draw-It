package edu.eci.arsw.drawit;

import edu.eci.arsw.drawit.model.Jugador;
import edu.eci.arsw.drawit.persistence.DrawItException;
import edu.eci.arsw.drawit.persistence.DrawitPersistence;
import edu.eci.arsw.drawit.services.DrawItServices;
import edu.eci.arsw.drawit.model.Sala;
import edu.eci.arsw.drawitapi.DrawItAPIApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DrawItAPIApplication.class)
public class DrawItServicesTest {
    @Autowired
    @Qualifier("drawItServices")
    DrawItServices service;

    @Test 
    public void shouldAddSala(){
        Sala s = new Sala();
        s.setCodigo("codigo");
        try{
            service.addNewSala(s);
            assertEquals(s.getCodigo(),service.getSala("codigo").getCodigo());
        }catch(DrawItException e){
            fail("Deberia agregar sala.");
        }
    }

    @Test
    public void shouldNotAddSala(){
        Sala s = new Sala();
        s.setCodigo("codigo");
        try{
            service.addNewSala(s);
            service.addNewSala(s);
            fail("Deberia fallar.");
        }catch(DrawItException e){
            assertEquals(e.getMessage(),"La sala " + s + "ya existe");
        }
    }

    @Test 
    public void shouldAddJugadorToSala(){
        Sala s = new Sala(new Jugador("autor"));
        s.setCodigo("codigo");
        try{
            service.addNewSala(s);
            Jugador j = new Jugador("jugador");
            service.addJugadorToSala(j, "codigo");
            assertEquals(service.getSala("codigo").getJugadores().get(1).getUsuario(),j.getUsuario());
        }catch(DrawItException e){
            fail("No Deberia fallar.");
        }
    } 

    @Test 
    public void shouldNotAddJugadorToSala(){
        Sala s = new Sala(new Jugador("autor"));
        s.setCodigo("codigo");
        try{
            service.addNewSala(s);
            Jugador j = new Jugador("jugador");
            service.addJugadorToSala(j, "codigo");
            service.addJugadorToSala(j, "codigo");
            fail("Deberia fallar.");
        }catch(DrawItException e){
            assertEquals(e.getMessage(),"La sala no existe o ya hay un jugador con ese nombre");
        }
    }

    @Test
    public void shouldGetPainterSalaTeam1(){
        Sala s = new Sala(new Jugador("autor"));
        s.setCodigo("codigo");
        try{
            service.addNewSala(s);
            for (int i = 0; i < 7; i++) {
                service.addJugadorToSala(new Jugador("jugador"+i),"codigo");
            }
            service.getEquiposBySalaAndAuthor("codigo","autor");
            assertEquals(s.getJugadores().get(0).getUsuario(),service.getPainterName("codigo","1"));
        }catch(DrawItException e){
            e.printStackTrace();
            fail("Deberia fallar.");
        }
    }

    @Test
    public void shouldGetPainterSalaTeam2(){
        Sala s = new Sala(new Jugador("autor"));
        s.setCodigo("codigo");
        try{
            service.addNewSala(s);
            for (int i = 0; i < 7; i++) {
                service.addJugadorToSala(new Jugador("jugador"+i),"codigo");
            }
            service.getEquiposBySalaAndAuthor("codigo","autor");
            assertEquals(s.getJugadores().get(4).getUsuario(),service.getPainterName("codigo","2"));
        }catch(DrawItException e){
            e.printStackTrace();
            fail("Deberia fallar.");
        }
    }
}