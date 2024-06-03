package springpzn.webmvc.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.MockMvcBuilder.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class ModelAttributeControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @Test
    void createPersonTest() throws Exception{
        mockMvc.perform(
            post("/person")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("firstName", "Eko")
                .param("middleName", "A")
                .param("lastName", "B")
                .param("email", "eko@g.com")
                .param("phone", "123456")
        ).andExpectAll(
            status().isOk(),
            content().string(Matchers.containsString("Success create person Eko A B with email eko@g.com and phone 123456"))
        );
    }
    
    @Test
    void createPersonTestValidationError() throws Exception{
        mockMvc.perform(
            post("/person")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("firstName", "Eko")
                .param("middleName", "A")
                .param("lastName", "B")
                .param("email", "eko@g.com")
        ).andExpectAll(
            status().isBadRequest(),
            content().string(Matchers.containsString("Validation Error"))
        );
    }


    @Test
    void createPersonBindingTestValidationError() throws Exception{
        mockMvc.perform(
            post("/personBinding")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("firstName", "Eko")
                .param("middleName", "A")
                // .param("lastName", "B")
                // .param("email", "eko@g.com")
        ).andExpectAll(
            status().isBadRequest(),
            content().string(Matchers.containsString("You send invalid data"))
        );
    }

    @Test
    void createPersonAddressTest() throws Exception{
        mockMvc.perform(
            post("/personAddress")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("firstName", "Eko")
                .param("middleName", "A")
                .param("lastName", "B")
                .param("email", "eko@g.com")
                .param("phone", "123456")
                .param("address.street", "Menado")
                .param("address.city", "Mexico City")
                .param("address.country", "Mexico")
                .param("address.postalCode", "123456")
        ).andExpectAll(
            status().isOk(),
            content().string(Matchers.containsString("Success create person Eko A B with email eko@g.com and phone 123456 with address Menado Mexico City Mexico 123456"))
        );
    }

    @Test
    void createPersonListTest() throws Exception{
        mockMvc.perform(
            post("/personAddress")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("firstName", "Eko")
                .param("middleName", "A")
                .param("lastName", "B")
                .param("email", "eko@g.com")
                .param("phone", "123456")
                .param("address.street", "Menado")
                .param("address.city", "Mexico City")
                .param("address.country", "Mexico")
                .param("address.postalCode", "123456")
                .param("hobies[0]", "coding")
                .param("hobies[1]", "reading")
                .param("socialMedia[0].name", "Facebook")
                .param("socialMedia[0].location", "www.facebook.com")
                .param("socialMedia[1].name", "Instagram")
                .param("socialMedia[1].location", "www.instagram.com")
                ).andExpectAll(
            status().isOk(),
            content().string(Matchers.containsString("Success create person Eko A B with email eko@g.com and phone 123456 with address Menado Mexico City Mexico 123456"))
        );
    }

}
