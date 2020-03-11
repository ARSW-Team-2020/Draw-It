package edu.eci.arsw.blueprints.persistence.impl;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.Filter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("redundant")
public class RedundantFilter implements Filter {

    public Blueprint planoFiltrado;

    @Override
    public Blueprint filtrar(Blueprint blueprint) {
        ArrayList<Point> listaDePuntos = new ArrayList<>();
        for (Point point: blueprint.getPoints()) {
            if (!listaDePuntos.contains(point)){
                listaDePuntos.add(point);
            }
        }
        planoFiltrado = new Blueprint(blueprint.getAuthor(), blueprint.getName(), listaDePuntos);
        return planoFiltrado;
    }

}
