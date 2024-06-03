package be.springvalidation;

import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import be.springvalidation.data.Foo;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;

@SpringBootTest
public class PalindromeTest {
    
    @Autowired
    private Validator validator;

    @Test
    void palindromeValid(){
        Set<ConstraintViolation<Foo>> constraintViolations = validator.validate(new Foo("kodok"));
        Assertions.assertTrue(constraintViolations.isEmpty());
    }
    
    @Test
    void palindromeNotValid(){
        Set<ConstraintViolation<Foo>> constraintViolations = validator.validate(new Foo("eko"));
        Assertions.assertFalse(constraintViolations.isEmpty());
        Assertions.assertEquals(1, constraintViolations.size());
    }

    @Test
    void palindromeInvalidMessage(){
        Set<ConstraintViolation<Foo>> constraingViolations = validator.validate(new Foo("eko"));
        Assertions.assertFalse(constraingViolations.isEmpty());
        Assertions.assertEquals(1, constraingViolations.size());

        String message = constraingViolations.stream().findFirst().get().getMessage();
        Assertions.assertEquals("Data bukan palindrome", message);
    }

}
