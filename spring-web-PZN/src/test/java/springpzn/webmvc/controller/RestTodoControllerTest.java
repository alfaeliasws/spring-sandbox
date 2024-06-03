package springpzn.webmvc.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.MockMvcBuilder.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class RestTodoControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @Test
    void addTodoTest() throws Exception{
        mockMvc.perform(
            post("/todos")
                    .accept(MediaType.APPLICATION_JSON)
                .param("todo","Eko")
        ).andExpectAll(
            status().isOk(),
            content().string(Matchers.containsString("Eko"))
        );
    }

    @Test
    void getTodoTest() throws Exception{
        mockMvc.perform(
            get("/todos")
                .accept(MediaType.APPLICATION_JSON)
        ).andExpectAll(
            status().isOk(),
            content().string(Matchers.containsString("Eko"))
        );
    }

}
