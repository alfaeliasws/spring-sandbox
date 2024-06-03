package be.springvalidation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import be.springvalidation.helper.SayHello;
import jakarta.validation.ConstraintViolationException;

@SpringBootTest
public class SayHelloTest {
    
    @Autowired
    private SayHello sayHello;


    @Test
    void testSuccess(){
        String message = sayHello.sayHello("John");
        Assertions.assertEquals("Hello John", message);
    }
    
    @Test
    void testError(){
        Assertions.assertThrows(ConstraintViolationException.class, () -> 
            sayHello.sayHello("")
        );

    }

}
