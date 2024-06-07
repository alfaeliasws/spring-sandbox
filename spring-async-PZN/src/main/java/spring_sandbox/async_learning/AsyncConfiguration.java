package spring_sandbox.async_learning;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AsyncConfiguration {
    
    @Bean
    public Executor taskExecutor(){
        return Executors.newVirtualThreadPerTaskExecutor();
    };
   
    @Bean
    public Executor singleTaskExecutor(){
        return Executors.newSingleThreadExecutor();
    };

    @Bean
    public ScheduledExecutorService taskScheduler(){
        return Executors.newScheduledThreadPool(10);
    }


}
