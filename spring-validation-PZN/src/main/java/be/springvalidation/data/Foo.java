package be.springvalidation.data;

import be.springvalidation.validation.Palindrome;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Foo {
    
    @Palindrome
    private String bar;

}
