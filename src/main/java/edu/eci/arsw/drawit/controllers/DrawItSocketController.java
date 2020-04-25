package edu.eci.arsw.drawit.controllers;

import edu.eci.arsw.drawit.model.ChatMessage;
import edu.eci.arsw.drawit.model.Line;
import edu.eci.arsw.drawit.model.Sala;
import edu.eci.arsw.drawit.persistence.DrawitPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class DrawItSocketController {



    @Autowired
    @Qualifier("Action")
    DrawitPersistence cache;

    @MessageMapping("/union/{name}")
    @SendTo("/topic/{name}")
    public List<String> informarNuevoJugador(@DestinationVariable String name) throws Exception {
        System.out.println("Se ha unido alguien a la sala "+name);
        return cache.getJugadoresBySala(name);
    }

    @MessageMapping("/{name}/empezar")
    @SendTo("/topic/{name}/empezar")
    public String empezarPartida(@DestinationVariable String name) throws Exception {
        System.out.println("La partida "+name+" ha empezado");
        String s = "-";
        return s;
    }

    @MessageMapping("/{name}/palabra")
    @SendTo("/topic/{name}/palabra")
    public String getPalabra(@DestinationVariable String name) throws Exception {
        String palabra= cache.getPalabra();
        System.out.println("Se obtivo la palabra "+ palabra);
        return palabra;
    }

    @MessageMapping("/{name}/chat/{equipo}")
    @SendTo("/topic/{name}/chat/{equipo}")
    public ChatMessage chatPartidaEquipo(@DestinationVariable String name,@DestinationVariable String equipo,@Payload ChatMessage chatMessage){
        System.out.println("Mensaje en la sala "+name+" del equipo "+equipo+" con mensaje: "+chatMessage.getContent());
        return chatMessage;
    }

    @MessageMapping("/{name}/dibujar/{equipo}")
    @SendTo("/topic/{name}/dibujar/{equipo}")
    public Line dibujarTableroEquipo(@DestinationVariable String name, @DestinationVariable String equipo, Line linea){
        //System.out.println("Mensaje en la sala "+name+" del equipo "+equipo+" con mensaje: "+linea.toString());
        return linea;
    }

    @MessageMapping("/{name}/borrar/{equipo}")
    @SendTo("/topic/{name}/borrar/{equipo}")
    public ChatMessage borrarTableroEquipo(@DestinationVariable String name, @DestinationVariable String equipo, @Payload ChatMessage erase){
        //System.out.println
        return erase;
    }
    @MessageMapping("/{name}/painterName/{equipo}")
    @SendTo("/topic/{name}/painterName/{equipo}")
    public ChatMessage sendPainterName(@DestinationVariable String name, @DestinationVariable String equipo,@Payload ChatMessage painter) throws Exception{
        String localName = cache.getPainterName(name, equipo);
        painter.setContent(localName);
        System.out.println(localName);
        return painter;
    }




}