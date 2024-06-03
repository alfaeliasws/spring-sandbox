package springpzn.webmvc.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.MockMvcBuilder.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import javax.print.attribute.standard.Media;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import springpzn.webmvc.model.HelloRequest;
import springpzn.webmvc.model.HelloResponse;

@SpringBootTest
@AutoConfigureMockMvc
public class BodyControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void bodyHello() throws Exception{
        HelloRequest request = new HelloRequest();
        request.setName("Eko");

        mockMvc.perform(
            post("/body/hello")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
        ).andExpectAll(
            status().isOk()
        ).andExpect(result -> {
            String responseBody = result.getResponse().getContentAsString();
            HelloResponse helloResponse = objectMapper.readValue(responseBody, HelloResponse.class);
            Assertions.assertEquals("Hello Eko", helloResponse.getHello());
        }
        );

    }
    
}
