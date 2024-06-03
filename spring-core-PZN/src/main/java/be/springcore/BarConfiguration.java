package be.springcore;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import be.springcore.data.Bar;

@Configuration
public class BarConfiguration {
    
    @Bean
    public Bar bar(){
        return new Bar();
    }

}