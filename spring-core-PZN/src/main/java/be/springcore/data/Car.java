package be.springcore.data;

import org.springframework.stereotype.Component;

import be.springcore.aware.IdAware;
import lombok.Getter;

@Component
public class Car implements IdAware {
    
    @Getter
    private String id;

    @Override
    public void setId(String id){
        this.id = id;
    }

}
