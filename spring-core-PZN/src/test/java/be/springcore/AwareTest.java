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
import be.springcore.service.AuthService;

public class AwareTest {
    
    @Configuration 
    @Import({AuthService.class})
    public static class TestConfiguration {

    }

    private ConfigurableApplicationContext applicationContext;

    @BeforeEach
    void setUp(){
        applicationContext = new AnnotationConfigApplicationContext(TestConfiguration.class);
        applicationContext.registerShutdownHook();
    }   

    @Test
    void testAware(){

        AuthService authService = applicationContext.getBean(AuthService.class);

        Assertions.assertEquals("be.springcore.service.AuthService", authService.getBeanName());
        Assertions.assertNotNull(authService.getApplicationContext());
        Assertions.assertSame(applicationContext, authService.getApplicationContext());

    }

}
