package be.springcore;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class CyclicTest {
    
    // private ApplicationContext applicationContext;

    // @BeforeEach
    // void setUp(){
    //     applicationContext = new AnnotationConfigApplicationContext(DependencyInjectionConfiguration.class);
    // }
    
    @Test
    void testCyclic(){
    
        // ApplicationContext context = new AnnotationConfigApplicationContext(CyclicConfiguration.class);
        
        Assertions.assertThrows(Throwable.class, () -> {
            ApplicationContext context = new AnnotationConfigApplicationContext(CyclicConfiguration.class);
        });
    
    }

}
