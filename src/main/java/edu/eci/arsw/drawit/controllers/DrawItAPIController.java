/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.drawit.controllers;

import edu.eci.arsw.drawit.model.Jugador;
import edu.eci.arsw.drawit.model.Sala;
import edu.eci.arsw.drawit.persistence.DrawItException;
import edu.eci.arsw.drawit.services.DrawItServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping(value = "/drawIt")
public class DrawItAPIController {

    @Autowired
    @Qualifier("drawItServices")
    DrawItServices drawItServices;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> crearSala(@RequestBody String usuario) {
        usuario=usuario.substring(0,usuario.length()-1);
        Jugador autor= new Jugador(usuario);
        Sala sala= new Sala(autor);
        try {
            drawItServices.addNewSala(sala);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DrawItException ex) {
            Logger.getLogger(DrawItAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @RequestMapping(value = {"/1/{usuario}"}, method = RequestMethod.GET)
    public ResponseEntity<?> getSalaCreada(@PathVariable() String usuario) {
        Jugador autor= new Jugador(usuario);
        Sala sala= new Sala(autor);
        try {
            return new ResponseEntity<>(drawItServices.getSalaCreada(sala), HttpStatus.ACCEPTED);
        } catch (DrawItException e) {
            Logger.getLogger(DrawItAPIController.class.getName()).log(Level.SEVERE, null, e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getAllSalas() {
        try {
            return new ResponseEntity<>(drawItServices.getSalas(), HttpStatus.ACCEPTED);
        } catch (DrawItException ex) {
            Logger.getLogger(DrawItAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = {"/{codigo}/{usuario}"}, method = RequestMethod.PUT)
    public ResponseEntity<?> actualizarSala(@PathVariable() String codigo, @PathVariable() String usuario) {
        try {
            Jugador jugador= new Jugador(usuario);
            drawItServices.addJugadorToSala(jugador,codigo);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (DrawItException ex) {
            Logger.getLogger(DrawItAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @RequestMapping(value = {"/{codigo}"}, method = RequestMethod.GET)
    public ResponseEntity<?> getJugadoresBySala(@PathVariable() String codigo) {
        System.out.println(codigo);
        try {
            return new ResponseEntity<>(drawItServices.getJugadoresBySala(codigo), HttpStatus.ACCEPTED);
        } catch (DrawItException e) {
            Logger.getLogger(DrawItAPIController.class.getName()).log(Level.SEVERE, null, e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = {"/{codigo}/{autor}"}, method = RequestMethod.GET)
    public ResponseEntity<?> getEquiposBySalaAndAuthor(@PathVariable() String codigo, @PathVariable() String autor) {
        try {
            String[] teamAndUsers = drawItServices.getEquiposBySalaAndAuthor(codigo, autor);
            return new ResponseEntity<>(teamAndUsers, HttpStatus.ACCEPTED);
        } catch (DrawItException e) {
            Logger.getLogger(DrawItAPIController.class.getName()).log(Level.SEVERE, null, e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = {"/palabra/{codigo}/{equipo}"}, method = RequestMethod.GET)
    public ResponseEntity<?> getPalabra(@PathVariable() String codigo,@PathVariable() int equipo){
        try{
            return new ResponseEntity<>(drawItServices.getPalabra(codigo,equipo), HttpStatus.ACCEPTED);
        }catch(DrawItException e){
            Logger.getLogger(DrawItAPIController.class.getName()).log(Level.SEVERE, null, e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}