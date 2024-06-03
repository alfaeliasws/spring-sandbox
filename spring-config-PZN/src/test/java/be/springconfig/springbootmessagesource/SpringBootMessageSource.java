package be.springconfig.springbootmessagesource;

import java.util.Locale;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.stereotype.Component;

import be.springconfig.messagesource.MessageSourceTest.TestApplication;
import lombok.Setter;

@SpringBootTest(classes = SpringBootMessageSource.TestApplication.class)
public class SpringBootMessageSource {
    
    @Autowired
    private TestApplication.SampleSource sampleSource;

    @Test
    void testHelloJohn(){
        Assertions.assertEquals("Hello John", sampleSource.helloJohn(Locale.of("ENGLISH")));
        Assertions.assertEquals("Halo John", sampleSource.helloJohn(Locale.of("in_ID")));
    }

    @SpringBootApplication
    public static class TestApplication{

        @Component
        public static class SampleSource implements MessageSourceAware{

            @Setter
            private MessageSource messageSource;

            public String helloJohn(Locale locale){
                return messageSource.getMessage("hello", new Object[]{"John"}, locale);
            } 

        }

    }

}
