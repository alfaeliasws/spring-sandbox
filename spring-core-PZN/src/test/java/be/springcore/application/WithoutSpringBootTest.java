package be.springcore.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import be.springcore.data.Car;
import be.springcore.data.Foo;
import be.springcore.processor.IdGeneratorPostProcessor;

public class WithoutSpringBootTest {
    
    private ConfigurableApplicationContext applicationContext;

    @BeforeEach
    void setUp(){
        applicationContext = new AnnotationConfigApplicationContext(FooApplication.class);
        applicationContext.registerShutdownHook();
    }   

    @Test
    void testFoo(){
        Foo foo = applicationContext.getBean(Foo.class);
    }

}
