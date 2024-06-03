package be.springcore;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import be.springcore.data.Car;
import be.springcore.processor.IdGeneratorPostProcessor;

public class BeanPostProcessorTest {
    
    @Configuration 
    @Import({Car.class, IdGeneratorPostProcessor.class})
    public static class TestConfiguration {

    }

    private ConfigurableApplicationContext applicationContext;

    @BeforeEach
    void setUp(){
        applicationContext = new AnnotationConfigApplicationContext(TestConfiguration.class);
        applicationContext.registerShutdownHook();
    }   

    @Test
    void testCar(){
        Car car = applicationContext.getBean(Car.class);
        
        System.out.println(car.getId());

        Assertions.assertNotNull(car.getId());
        
    }

}
