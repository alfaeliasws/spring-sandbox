package be.springcore.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import be.springcore.data.Foo;

@Configuration
public class FooConfiguration {
    
    @Bean
    public Foo foo(){
        return new Foo();
    }

}
