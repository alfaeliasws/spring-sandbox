package be.springcore;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import be.springcore.data.Car;
import be.springcore.processor.IdGeneratorPostProcessor;
import be.springcore.processor.PrefixIdGeneratorPostProcessor;

public class OrderedTest {
    
    @Configuration 
    @Import({
        Car.class, 
        IdGeneratorPostProcessor.class,
        PrefixIdGeneratorPostProcessor.class
    })
    public static class OrderedConfiguration {

    }

    private ConfigurableApplicationContext applicationContext;

    @BeforeEach
    void setUp(){
        applicationContext = new AnnotationConfigApplicationContext(OrderedConfiguration.class);
        applicationContext.registerShutdownHook();
    }   

    @Test
    void testCar(){
        
        Car car = applicationContext.getBean(Car.class);
        Assertions.assertNotNull(car.getId());
        Assertions.assertTrue(car.getId().startsWith("PZN-"));

    }

}
