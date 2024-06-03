package be.springcore;

import java.util.Optional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import be.springcore.data.Foo;
import be.springcore.data.Bar;
import be.springcore.data.FooBar;

@Configuration
public class OptionalConfiguration {
    
    @Bean
    public Foo foo(){
        return new Foo();
    }

    @Bean
    public FooBar fooBar(Optional<Foo> foo, Optional<Bar> bar){
        return new FooBar(foo.orElse(null), bar.orElse(null));
    }

}
