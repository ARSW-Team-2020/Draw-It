package edu.eci.arsw.drawit.persistence.impl;

import edu.eci.arsw.drawit.model.Blueprint;
import edu.eci.arsw.drawit.model.Point;
import edu.eci.arsw.drawit.persistence.Filter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

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
