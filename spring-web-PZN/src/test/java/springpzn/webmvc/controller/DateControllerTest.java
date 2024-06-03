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
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class DateControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @Test
    void test() throws Exception{
        mockMvc.perform(
            get("/date").queryParam("date", "2020-10-10")
        )
            .andExpectAll(
                status().isOk(),
                content().string(Matchers.containsString("Date: 20201010"))
        );
    }

}
