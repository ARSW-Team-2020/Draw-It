package edu.eci.arsw.drawit.controllers;

import edu.eci.arsw.drawit.model.Sala;
import edu.eci.arsw.drawit.persistence.DrawitPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
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
        String s = "-m-";
        return s;
    }

}