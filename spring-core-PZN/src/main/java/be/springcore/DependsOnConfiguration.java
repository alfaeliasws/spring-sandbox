package be.springcore;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Lazy;

import be.springcore.data.Bar;
import be.springcore.data.Foo;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class DependsOnConfiguration {
    
    @Lazy
    @DependsOn({
        "bar"
    })
    @Bean
    public Foo foo(){
        log.info("Create New Foo");
        return new Foo();
    }
    
    @Bean 
    public Bar bar(){
        log.info("Create New Bar");
        return new Bar();
    }

}
