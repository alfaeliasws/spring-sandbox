package be.springvalidation;

import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import be.springvalidation.data.Person;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;

@SpringBootTest
public class ValidatorTest {
    
    @Autowired
    private Validator validator;

    @Test
    void validPerson(){
        var person = new Person("1", "John");

        Set<ConstraintViolation<Person>> constraintViolations = validator.validate(person);

        Assertions.assertTrue(constraintViolations.isEmpty());
    }

    @Test
    void personNotValid(){
        var person = new Person("", "");

        Set<ConstraintViolation<Person>> constraintViolations = validator.validate(person);
        Assertions.assertFalse(constraintViolations.isEmpty());
        Assertions.assertEquals(2, constraintViolations.size());
        

    }

}
