package be.springconfig.profileenvironment;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ActiveProfiles;

import lombok.Setter;


@SpringBootTest(classes = ProfileEnvironmentTest.TestApplication.class)
@ActiveProfiles({"production","local"})
public class ProfileEnvironmentTest {
    
    @Autowired
    private TestApplication.SampleProfile sampleProfile;
    
    @Test
    void testProfile(){
        Assertions.assertArrayEquals(new String[]{"production", "local"}, sampleProfile.getActiveProfiles());
        // Assertions.assertEquals("Hello John From Production", sayHello.say("John"));
    }

    @SpringBootApplication
    public static class TestApplication{

        @Component
        public static class SampleProfile implements EnvironmentAware {

            @Setter
            private Environment environment;

            public String[] getActiveProfiles(){
                return environment.getActiveProfiles();
            }
        
            
        }

    }

}
