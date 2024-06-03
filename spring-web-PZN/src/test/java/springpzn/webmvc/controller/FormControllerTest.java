package springpzn.webmvc.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.MockMvcBuilder.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.test.web.servlet.MockMvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

@SpringBootTest
@AutoConfigureMockMvc
public class FormControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @Test
    void formHello() throws Exception{
        mockMvc.perform(
            post("/form/hello")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("name", "Eko")
        ).andExpectAll(
            status().isOk(),
            content().string(Matchers.containsString("Hello Eko"))
        );
    }

    @Test
    void formHelloHtml() throws Exception{
        mockMvc.perform(
            post("/form/helloHtml")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("name", "Eko")
        ).andExpectAll(
            status().isOk(),
            header().string(HttpHeaders.CONTENT_TYPE, Matchers.containsString(MediaType.TEXT_HTML_VALUE)),
            content().string(Matchers.containsString("Hello Eko"))
        );
    }

    @Test
    void createPerson() throws Exception{
        mockMvc.perform(
            post("/form/person")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("name", "Eko")
                .param("birthDate", "1990-10-10")
                .param("address", "Indonesia")
        ).andExpectAll(
            status().isOk(),
            content().string(Matchers.containsString(
                "Success create Person with name : Eko, " + "birthDate : 1990-10-10, " + "address : Indonesia"
            ))
        );
    }


}
