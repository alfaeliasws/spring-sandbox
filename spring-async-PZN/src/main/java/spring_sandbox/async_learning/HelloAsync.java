package spring_sandbox.async_learning;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class HelloAsync {
    
    @Async("singleTaskExecutor")
    @SneakyThrows
    public void hello(){
        Thread.sleep(Duration.ofSeconds(2));
        log.info("hello after 2 seconds {}", Thread.currentThread());
    }

    @Async
    @SneakyThrows
    public Future<String> hello(final String name){
        CompletableFuture<String> future = new CompletableFuture<>();
        Thread.sleep(Duration.ofSeconds(2));
        future.complete("Hello " + name + " from Thread " + Thread.currentThread());
        return future;

    }
}
