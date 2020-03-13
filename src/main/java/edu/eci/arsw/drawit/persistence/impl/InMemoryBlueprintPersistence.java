/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.drawit.persistence.impl;

import edu.eci.arsw.drawit.model.Blueprint;
import edu.eci.arsw.drawit.model.Point;
import edu.eci.arsw.drawit.persistence.BlueprintNotFoundException;
import edu.eci.arsw.drawit.persistence.BlueprintPersistenceException;
import edu.eci.arsw.drawit.persistence.BlueprintsPersistence;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author hcadavid
 */
@Service("inMemory")
public class InMemoryBlueprintPersistence implements BlueprintsPersistence {

    private final Map<Tuple<String, String>, Blueprint> blueprints = new ConcurrentHashMap<>();

    public InMemoryBlueprintPersistence() {
        //load stub data
        Point[] pts = new Point[]{new Point(140, 140), new Point(115, 115)};
        Blueprint bp = new Blueprint("Rocha", "Plano0 ", pts);
        blueprints.put(new Tuple<>(bp.getAuthor(), bp.getName()), bp);

        Blueprint bp1 = new Blueprint("Frasica", "Plano1", pts);
        blueprints.put(new Tuple<>(bp1.getAuthor(), bp1.getName()), bp1);

        Blueprint bp2 = new Blueprint("Rocha", "Plano2", pts);
        blueprints.put(new Tuple<>(bp2.getAuthor(), bp2.getName()), bp2);

        Blueprint bp3 = new Blueprint("Frasica", "Plano3", pts);
        blueprints.put(new Tuple<>(bp3.getAuthor(), bp3.getName()), bp3);

    }

    @Override
    public void saveBlueprint(Blueprint bp) throws BlueprintPersistenceException {
        if (blueprints.containsKey(new Tuple<>(bp.getAuthor(), bp.getName()))) {
            throw new BlueprintPersistenceException("The given blueprint already exists: " + bp);
        } else {
            blueprints.put(new Tuple<>(bp.getAuthor(), bp.getName()), bp);
        }
    }

    @Override
    public Blueprint getBlueprint(String author, String bprintname) throws BlueprintNotFoundException {
        if (!blueprints.containsKey(new Tuple<>(author, bprintname))) {
            throw new BlueprintNotFoundException("El plano no existe.");
        }
        return blueprints.get(new Tuple<>(author, bprintname));
    }

    @Override
    public HashSet<Blueprint> getAllBlueprints() throws BlueprintNotFoundException {
        HashSet<Blueprint> conjunto = new HashSet<>(blueprints.values());
        if (conjunto.size() == 0) {
            throw new BlueprintNotFoundException("El autor no existe, so sorry :(");
        }
        return conjunto;
    }

    @Override
    public HashSet<Blueprint> getBlueprintsByAuthor(String author) throws BlueprintNotFoundException {
        HashSet<Blueprint> conjunto = new HashSet<>();

        for (Tuple<String, String> key : blueprints.keySet()) {
            if (key.getElem1().equals(author)) {
                conjunto.add(blueprints.get(key));
            }
        }

        if (conjunto.size() == 0) {
            throw new BlueprintNotFoundException("El autor no existe, so sorry :(");
        }

        return conjunto;
    }

    @Override
    public void updateBlueprint(Blueprint bp) throws BlueprintNotFoundException {
        if (!blueprints.containsKey(new Tuple<>(bp.getAuthor(), bp.getName()))) {
            throw new BlueprintNotFoundException("El plano no existe.");
        }
        Blueprint plano = blueprints.get(new Tuple<>(bp.getAuthor(), bp.getName()));
        plano.setPoints(bp.getPoints());

    }

}
