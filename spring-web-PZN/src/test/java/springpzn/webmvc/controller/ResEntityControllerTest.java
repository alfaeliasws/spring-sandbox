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

import jakarta.servlet.http.Cookie;

@SpringBootTest
@AutoConfigureMockMvc
public class ResEntityControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @Test
    void loginSuccess() throws Exception{
        mockMvc.perform(
            post("/auth/login")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("username", "eko")
                .param("password", "rahasia")
        ).andExpectAll(
            status().isOk(),
            content().string(Matchers.containsString("OK"))
        );
    }

    @Test
    void loginFailed() throws Exception{
        mockMvc.perform(
            post("/auth/login")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("username", "Eko")
                .param("password", "johnson")
        ).andExpectAll(
            status().isUnauthorized(),
            content().string(Matchers.containsString("KO"))
        );
    }

    @Test
    void loginSuccessWithCookie() throws Exception{
        mockMvc.perform(
            post("/auth/loginCookie")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("username", "eko")
                .param("password", "rahasia")
        ).andExpectAll(
            status().isOk(),
            content().string(Matchers.containsString("OK")),
            cookie().value("username",Matchers.is("eko"))
        );
    }

    @Test
    void loginSuccessSession() throws Exception{
        mockMvc.perform(
            post("/auth/loginSession")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("username", "eko")
                .param("password", "rahasia")
        ).andExpectAll(
            status().isOk(),
            content().string(Matchers.containsString("OK")),
            cookie().value("username",Matchers.is("eko"))
        );
    }

    @Test
    void getUser() throws Exception{
        mockMvc.perform(
            get("/auth/user")
                .cookie(new Cookie("username", "eko"))
        ).andExpectAll(
            status().isOk(),
            content().string(Matchers.containsString("Hello eko"))
        );
    }
}
