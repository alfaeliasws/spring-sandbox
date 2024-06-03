package springpzn.webmvc.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class HelloServiceImplTest {
    
    @Autowired
    private HelloService helloService; 


    @Test
    void hello(){
        assertEquals("Hello Guest",helloService.hello(null));
        assertEquals("Hello Eko",helloService.hello("Eko"));
    }

}
