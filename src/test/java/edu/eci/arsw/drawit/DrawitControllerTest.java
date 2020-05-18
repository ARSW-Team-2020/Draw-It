package edu.eci.arsw.drawit;

import edu.eci.arsw.drawit.model.Jugador;
import edu.eci.arsw.drawit.model.Sala;
import edu.eci.arsw.drawit.services.DrawItServices;
import edu.eci.arsw.drawitapi.DrawItAPIApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={ DrawItAPIApplication.class })
@AutoConfigureMockMvc
public class  DrawitControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private DrawItServices services;
    @Test
    public void shouldCrearSala() throws Exception {
        mvc.perform(
              MockMvcRequestBuilders.post("/drawIt")
              .contentType(MediaType.APPLICATION_JSON)
              .content("usuario")
              .accept(MediaType.APPLICATION_JSON))
              .andExpect(status().isCreated());
    }

    @Test
    public void shouldActualizarSala() throws Exception {
        Sala s = new Sala(new Jugador("autor"));
        s.setCodigo("codigo");
        services.addNewSala(s);
        mvc.perform(
                MockMvcRequestBuilders.put("/drawIt/codigo/jugador2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(""))
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldNotActualizarSala() throws Exception {
        Sala s = new Sala(new Jugador("autor"));
        s.setCodigo("codigo");
        services.addNewSala(s);
        mvc.perform(
                MockMvcRequestBuilders.put("/drawIt/codigo/jugador2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(""))
                .andExpect(status().isNoContent());
        mvc.perform(
                MockMvcRequestBuilders.put("/drawIt/codigo/jugador2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(""))
                .andExpect(status().isForbidden());
    }

    @Test
    public void shouldGetJugadoresBySala() throws Exception {
        Sala s = new Sala(new Jugador("autor"));
        s.setCodigo("codigo");
        services.addNewSala(s);
        mvc.perform(
                MockMvcRequestBuilders.get("/drawIt/codigo/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(""))
                .andExpect(status().isAccepted());
    }

    @Test
    public void shouldNotGetJugadoresBySala() throws Exception {
        mvc.perform(
                MockMvcRequestBuilders.get("/drawIt/codigo/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(""))
                .andExpect(status().isNotFound());
    }
}