package edu.eci.arsw.drawit.controllers;

import edu.eci.arsw.drawit.model.ChatMessage;
import edu.eci.arsw.drawit.model.Line;
import edu.eci.arsw.drawit.model.Sala;
import edu.eci.arsw.drawit.persistence.DrawItException;
import edu.eci.arsw.drawit.persistence.DrawitPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import java.util.List;

@Controller
public class DrawItSocketController {

    @Autowired
    @Qualifier("Action")
    DrawitPersistence cache;

    @MessageMapping("/union/{name}")
    @SendTo("/topic/{name}")
    public List<String> informarNuevoJugador(@DestinationVariable String name) throws DrawItException {
        System.out.println("Se ha unido alguien a la sala "+name);
        return cache.getJugadoresBySala(name);
    }

    @MessageMapping("/{name}/empezar")
    @SendTo("/topic/{name}/empezar")
    public String empezarPartida(@DestinationVariable String name,@Payload String fechaFin) throws DrawItException {
        System.out.println("La partida "+name+" ha empezado");
        Sala sala = cache.getSala(name);
        String[] equipo1 = new String[4];
        String[] equipo2 = new String[4];
        for(int i=0; i<4; i++){
            equipo1[i] = sala.getJugadores().get(i).getUsuario();
            equipo2[i] = sala.getJugadores().get(i+4).getUsuario();
        }
        sala.getEquipos().get(0).setJugadores(equipo1);
        sala.getEquipos().get(1).setJugadores(equipo2);
        sala.crearRonda();
        return fechaFin.toString();
    }


    @MessageMapping("/{name}/chat/{equipo}")
    @SendTo("/topic/{name}/chat/{equipo}")
    public ChatMessage chatPartidaEquipo(@DestinationVariable String name, @DestinationVariable String equipo, @Payload ChatMessage chatMessage){
        System.out.println("Mensaje en la sala "+name+" del equipo "+equipo+" con mensaje: "+chatMessage.getContent());
        return chatMessage;
    }

    @MessageMapping("/{name}/palabra/{equipo}")
    @SendTo("/topic/{name}/palabra/{equipo}")
    public String cambiaPalabra(@DestinationVariable String name,@DestinationVariable int equipo) throws DrawItException{
        System.out.println("Se cambia la palabra para "+name+" del equipo "+equipo);
        String palabra = cache.getPalabra(name,equipo);
        System.out.println(palabra);
        return palabra;
    }

        @MessageMapping("/{name}/dibujar/{equipo}")
    @SendTo("/topic/{name}/dibujar/{equipo}")
    public Line dibujarTableroEquipo(@DestinationVariable String name, @DestinationVariable String equipo, Line linea){
        return linea;
    }

    @MessageMapping("/{name}/borrar/{equipo}")
    @SendTo("/topic/{name}/borrar/{equipo}")
    public ChatMessage borrarTableroEquipo(@DestinationVariable String name, @DestinationVariable String equipo, @Payload ChatMessage erase){
        return erase;
    }

    @MessageMapping("/{name}/painterName/{equipo}")
    @SendTo("/topic/{name}/painterName/{equipo}")
    public ChatMessage sendPainterName(@DestinationVariable String name, @DestinationVariable String equipo,@Payload ChatMessage painter) throws Exception{
        System.out.println("Para el equipo: "+equipo+" Quien dibuja");
        String localName = cache.getPainterName(name, equipo);
        painter.setContent(localName);
        System.out.println(localName+" Going to Draw");
        return painter;
    }

    @MessageMapping("/{name}/ronda/{numero}")
    @SendTo("/topic/{name}/ronda/")
    public String avanzarRonda(@DestinationVariable String name,@DestinationVariable int numero,@Payload String fechaFin) throws DrawItException{
        System.out.println("Se avanza la ronda de :"+name);
        Sala s = cache.getSala(name);
        if (s.getRonda() == numero && s.cambiar() && s.getRonda() < 5){
            s.avanzarRonda();
            System.out.println("Se avanzo");
            return fechaFin;
        }
        return "-";
    }

    @MessageMapping("/{name}/round/{equipo}")
    @SendTo("/topic/{name}/round/{equipo}")
    public ChatMessage sendNextRound(@DestinationVariable String name, @DestinationVariable String equipo,@Payload ChatMessage round) throws Exception{
        cache.siguienteTurno(name,equipo);

        System.out.println(" Next Round ");
        return round;
    }

    @MessageMapping("/{name}/salir/{equipo}")
    @SendTo("/topic/{name}/salir/")
    public String salirSala(@DestinationVariable String name,@DestinationVariable int equipo, @Payload String player) throws Exception{
        System.out.println("Se salio "+player+" de la sala "+name+" del equipo "+equipo);
        Sala s  = cache.getSala(name);
        s.quitarJugador(equipo-1,player);
        return player;
    }

    @MessageMapping("/general")
    @SendTo("/topic/general/")
    public String salasGeneral() throws Exception{
        return "";
    }

    @MessageMapping("/{name}/puntaje/{equipo}")
    @SendTo("/topic/{name}/puntaje/{equipo}")
    public String getPuntajeByEquipos(@DestinationVariable String name, @DestinationVariable String equipo) throws DrawItException{
        return String.valueOf(cache.getPuntajeByEquipo(name,equipo));
    }

    @MessageMapping("/{name}/updatePuntaje/{equipo}")
    @SendTo("/topic/{name}/updatePuntaje/{equipo}")
    public ChatMessage updatePuntaje(@DestinationVariable String name, @DestinationVariable String equipo,@Payload ChatMessage puntaje) throws Exception{
        puntaje.setContent(String.valueOf(cache.updatePuntaje(name,equipo)));
        return puntaje;
    }
}
