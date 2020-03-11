package edu.eci.arsw.drawit.persistence.impl;

import edu.eci.arsw.drawit.model.Blueprint;
import edu.eci.arsw.drawit.model.Point;
import edu.eci.arsw.drawit.persistence.Filter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service("subsample")
public class SubsampleFilter implements Filter {

    public Blueprint planoFiltrado;

    @Override
    public Blueprint filtrar(Blueprint blueprint) {
        ArrayList<Point> lista = new ArrayList<>();
        for (int i = 0; i < blueprint.getPoints().size(); i += 2) {
            lista.add(blueprint.getPoints().get(i));

        }
        planoFiltrado = new Blueprint(blueprint.getAuthor(), blueprint.getName(), lista);
        return planoFiltrado;
    }
}
