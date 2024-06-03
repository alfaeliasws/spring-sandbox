package be.springcore;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import be.springcore.event.LoginSuccessEvent;
import be.springcore.listener.LoginAgainSuccessListener;
import be.springcore.listener.LoginSuccessListener;
import be.springcore.listener.UserListener;
import be.springcore.service.UserService;

public class EventListenerTest {
    @Configuration 
    @Import({UserService.class, UserListener.class, LoginSuccessListener.class, LoginAgainSuccessListener.class})
    public static class TestConfiguration {

    }

    private ConfigurableApplicationContext applicationContext;

    @BeforeEach
    void setUp(){
        applicationContext = new AnnotationConfigApplicationContext(TestConfiguration.class);
        applicationContext.registerShutdownHook();
    }   

    @Test
    void testEvent(){
        UserService userService = applicationContext.getBean(UserService.class);
        userService.login("eko", "eko");
        userService.login("eko", "salah");
        userService.login("kurniawan", "salah");
    }
}
