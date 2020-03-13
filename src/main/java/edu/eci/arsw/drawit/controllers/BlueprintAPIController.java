/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.drawit.controllers;

import java.util.logging.Level;
import java.util.logging.Logger;

import edu.eci.arsw.drawit.model.Blueprint;
import edu.eci.arsw.drawit.persistence.BlueprintNotFoundException;
import edu.eci.arsw.drawit.persistence.BlueprintPersistenceException;
import edu.eci.arsw.drawit.services.BlueprintsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/blueprints")
public class BlueprintAPIController {

    @Autowired
    @Qualifier("blueprintServices")
    BlueprintsServices bpp;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getAllBlueprints() {
        try {
            //obtener datos que se enviarán a través del API
            return new ResponseEntity<>(bpp.getAllBlueprints(), HttpStatus.ACCEPTED);
        } catch (BlueprintNotFoundException ex) {
            Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{author}", method = RequestMethod.GET)
    public ResponseEntity getBlueprintsByAuthor(@PathVariable String author) {
        try {
            return new ResponseEntity<>(bpp.getBlueprintsByAuthor(author), HttpStatus.ACCEPTED);
        } catch (BlueprintNotFoundException e) {
            Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = {"/{author}/{name}"}, method = RequestMethod.GET)
    public ResponseEntity getBlueprint(@PathVariable String author, @PathVariable String name) {
        try {
            return new ResponseEntity<>(bpp.getBlueprint(author, name), HttpStatus.ACCEPTED);
        } catch (BlueprintNotFoundException e) {
            Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> crearPlano(@RequestBody Blueprint plano) {
        try {
            bpp.addNewBlueprint(plano);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (BlueprintPersistenceException ex) {
            Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

    }


    @RequestMapping(value = {"/{author}/{name}"}, method = RequestMethod.PUT)
    public ResponseEntity<?> actualizarPlano(@RequestBody Blueprint plano) {
        try {
            bpp.updateBlueprint(plano);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (BlueprintNotFoundException ex) {
            Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

    }


}