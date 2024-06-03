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

@SpringBootTest
@AutoConfigureMockMvc   
public class PathVariableControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void orderProduct() throws Exception{
        mockMvc.perform(
            get("/orders/1/products/2")
                .header("X-TOKEN", "EKO")
        ).andExpectAll(
            status().isOk(),
            content().string(Matchers.containsString("Order : 1, Product : 2"))
        );
    }

}
