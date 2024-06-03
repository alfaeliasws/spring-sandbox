package be.springconfig.messagesource;

import java.util.Locale;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;

public class MessageSourceTest {
    
    private ApplicationContext applicationContext;

    private MessageSource messageSource;

    @BeforeEach
    void setUp(){
        applicationContext = new AnnotationConfigApplicationContext(TestApplication.class);

        messageSource = applicationContext.getBean(MessageSource.class);
    }

    @Test
    void testStringDefaultLocale(){
        String message = messageSource.getMessage("hello", new Object[]{"John"}, Locale.of("ENGLISH"));
        Assertions.assertEquals("Hello John", message);
    }

    @Test
    void testStringDefaultIndonesian(){
        String message = messageSource.getMessage("hello", new Object[]{"John"}, Locale.of("in_ID"));
        Assertions.assertEquals("Halo John", message);
    }


    @SpringBootApplication
    public static class TestApplication {

        @Bean
        public MessageSource messageSource(){
            ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
            messageSource.setBasenames("my");
            return messageSource;
        }

    }

}
