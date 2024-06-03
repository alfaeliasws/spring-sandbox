package be.springvalidation.helper;

import org.springframework.stereotype.Component;

import jakarta.validation.constraints.NotBlank;

@Component
public class SayHello implements ISayHello {
    
    public String sayHello(@NotBlank String name){
        return "Hello " + name;
    }

}
