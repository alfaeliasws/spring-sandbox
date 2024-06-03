package springpzn.webmvc.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.MockMvcBuilder.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import springpzn.webmvc.model.CreatePersonRequest;
import springpzn.webmvc.model.CreateSocialMediaRequest;

@SpringBootTest
@AutoConfigureMockMvc
public class JsonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createPersonJsonTest() throws Exception{
        CreatePersonRequest request =  new CreatePersonRequest();

        request.setFirstName("Joni");
        request.setMiddleName("Andreas");
        request.setLastName("Junaedi");
        request.setPhone("081211211211");
        request.setEmail("jaj@g.com");
        request.setHobbies(List.of("Coding","Reading","Swimming"));
        request.setSocialMedia(new ArrayList<>());
        request.getSocialMedia().add(new CreateSocialMediaRequest("facebook","facebook.com/Jaja"));
        request.getSocialMedia().add(new CreateSocialMediaRequest("instagram","instagram.com/Jaja"));

        String jsonRequest = objectMapper.writeValueAsString(request);

        mockMvc.perform(
            post("/api/person")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonRequest)
        ).andExpectAll(
            status().isOk(),
            content().json(jsonRequest)
        );
    }

    @Test
    void createPersonJsonTestValidationError() throws Exception{
        CreatePersonRequest request =  new CreatePersonRequest();

        request.setFirstName("Joni");
        request.setMiddleName("Andreas");
        request.setLastName("Junaedi");
        // request.setPhone("081211211211");
        request.setEmail("jaj@g.com");
        request.setHobbies(List.of("Coding","Reading","Swimming"));
        request.setSocialMedia(new ArrayList<>());
        request.getSocialMedia().add(new CreateSocialMediaRequest("facebook","facebook.com/Jaja"));
        request.getSocialMedia().add(new CreateSocialMediaRequest("instagram","instagram.com/Jaja"));

        String jsonRequest = objectMapper.writeValueAsString(request);

        mockMvc.perform(
            post("/api/person")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonRequest)
        ).andExpectAll(
            status().isBadRequest(),
            content().string(Matchers.containsString("Validation Error"))
            // content().json(jsonRequest)
        );
    }

}
