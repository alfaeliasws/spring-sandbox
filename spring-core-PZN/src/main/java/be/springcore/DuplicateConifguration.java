package be.springcore;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import be.springcore.data.Foo;

@Configuration
public class DuplicateConifguration {
    
    @Bean
    public Foo foo1(){
        return new Foo();
    }
    
    @Bean 
    public Foo foo2(){
        return new Foo();
    }

}
