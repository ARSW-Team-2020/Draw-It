/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.drawit.persistence;

import edu.eci.arsw.drawit.model.Blueprint;
import org.springframework.stereotype.Service;

import java.util.HashSet;

/**
 * @author hcadavid
 */
@Service
public interface BlueprintsPersistence {

    /**
     * @param bp the new blueprint
     * @throws BlueprintPersistenceException if a blueprint with the same name already exists,
     *                                       or any other low-level persistence error occurs.
     */
    public void saveBlueprint(Blueprint bp) throws BlueprintPersistenceException;

    /**
     * @param author     blueprint's author
     * @param bprintname blueprint's author
     * @return the blueprint of the given name and author
     * @throws BlueprintNotFoundException if there is no such blueprint
     */
    public Blueprint getBlueprint(String author, String bprintname) throws BlueprintNotFoundException;


    public HashSet<Blueprint> getAllBlueprints() throws BlueprintNotFoundException;

    public HashSet<Blueprint> getBlueprintsByAuthor(String author) throws BlueprintNotFoundException;

    public void updateBlueprint(Blueprint bp) throws BlueprintNotFoundException;

}
