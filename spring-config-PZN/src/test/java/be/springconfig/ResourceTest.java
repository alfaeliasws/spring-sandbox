package be.springconfig;

import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

public class ResourceTest {

    @Test
    void testResource() throws IOException{
        var resource = new ClassPathResource("/text/sample.txt");
    
        Assertions.assertNotNull(resource);
        Assertions.assertTrue(resource.exists());
        Assertions.assertNotNull(resource.getFile());

    }


}
