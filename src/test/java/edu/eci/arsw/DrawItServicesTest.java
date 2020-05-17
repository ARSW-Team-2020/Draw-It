package edu.eci.arsw;

import edu.eci.arsw.drawit.persistence.DrawItException;
import edu.eci.arsw.drawit.services.DrawItServices;
import edu.eci.arsw.drawit.model.Jugador;
import edu.eci.arsw.drawit.model.Sala;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
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
            assertEquals(s.getCodigo(),service.getSala("codigo"));
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
            assertEquals(e.getMessage(),"La sala " + s.getCodigo() + "ya existe");
        }
    }

    @Test 
    public void shouldAddJugadorToSala(){
        Sala s = new Sala();
        s.setCodigo("codigo");
        try{
            service.addNewSala(s);
            Jugador j = new Jugador("jugador");
            service.addJugadorToSala(j, "codigo");
            assertEquals(service.getSala("codigo").getJugadores().get(0).getUsuario(),j.getUsuario());
        }catch(DrawItException e){
            fail("No Deberia fallar.");
        }
    } 

    @Test 
    public void shouldNotAddJugadorToSala(){
        Sala s = new Sala();
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
}