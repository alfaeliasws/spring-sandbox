package be.springcore;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import be.springcore.data.Foo;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class BeanConfiguration {

    @Bean
    public Foo foo(){
        Foo foo = new Foo();
        log.info("Create Foo");
        return foo;
    }

}
