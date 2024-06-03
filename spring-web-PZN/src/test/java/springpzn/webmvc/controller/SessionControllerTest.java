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
import org.springframework.test.web.servlet.MockMvc;

import springpzn.webmvc.model.User;

@SpringBootTest
@AutoConfigureMockMvc
public class SessionControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @Test
    void getUserTest() throws Exception{
        mockMvc.perform(
            get("/user/current")
                .sessionAttr("user", new User("eko"))
        ).andExpectAll(
            status().isOk(),
            content().string(Matchers.containsString("Hello eko"))
        );
    }

    @Test
    void getUserInterceptorInvalidTest() throws Exception{
        mockMvc.perform(
            get("/user/current")
        ).andExpectAll(
            status().is3xxRedirection()
        );
    }


}
