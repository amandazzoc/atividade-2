package br.com.faculdade.atividade_2;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(DisciplinaController.class)
public class DisciplinaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void listarDeveRetornarListaComDisciplinas() throws Exception {
        mockMvc.perform(get("/disciplinas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value("Programação Web"))
                .andExpect(jsonPath("$[1].nome").value("Engenharia de Software"));
    }

    @Test
    public void buscarPorIdDeveRetornarDisciplinaQuandoExistir() throws Exception {
        mockMvc.perform(get("/disciplinas/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Programação Web"));
    }

    @Test
    public void buscarPorIdDeveRetornar404QuandoNaoExistir() throws Exception {
        mockMvc.perform(get("/disciplinas/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void criarDeveRetornar201ECriarDisciplina() throws Exception {
        Disciplina nova = new Disciplina(null, "Banco de Dados", "DSM", 40);
        String json = objectMapper.writeValueAsString(nova);

        mockMvc.perform(post("/disciplinas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.nome").value("Banco de Dados"));
    }
}
