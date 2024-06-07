package spring_sandbox.async_learning;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JobTest {
    
    @Autowired
    private Job job;
    
    @Test
    void job() throws InterruptedException {
        Thread.sleep(Duration.ofSeconds(5));
        assertEquals(1L, job.getValue());
    }

}
