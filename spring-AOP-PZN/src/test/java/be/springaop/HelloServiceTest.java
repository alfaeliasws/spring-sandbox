package be.springaop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import be.springaop.service.HelloService;

@SpringBootTest
public class HelloServiceTest {
    
    @Autowired
    private HelloService helloService;

    @Test
    void helloService(){
        Assertions.assertEquals("Hello Service", helloService.hello("Service"));
        Assertions.assertEquals("Hello Service Mantab", helloService.hello("Service", "Mantab"));
        Assertions.assertEquals("Bye Service", helloService.bye("Service"));

        helloService.test();
    }

}
