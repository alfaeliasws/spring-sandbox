package spring_sandbox.async_learning;

import java.time.Duration;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class HelloAsyncTest {
    
    @Autowired
    private HelloAsync helloAsync;

    @Test
    void helloAsync() throws InterruptedException{
        for (int i = 0; i < 10; i++) {
            helloAsync.hello();
        }

        log.info("after call hello async");
        Thread.sleep(Duration.ofSeconds(15));
    }

    @Test
    void helloName() throws ExecutionException, InterruptedException{
        Future<String> future = helloAsync.hello("John");
        log.info("after call hello(Eko)");
        String response = future.get();
        log.info(response);
    }
}
