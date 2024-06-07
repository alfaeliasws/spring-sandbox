package spring_sandbox.async_learning;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class Job {
    
    private AtomicLong atomicLong = new AtomicLong();

    @Scheduled(timeUnit = TimeUnit.SECONDS, initialDelay = 2, fixedDelay = 5)
    public void runJob(){
        Long value = atomicLong.incrementAndGet();
        log.info("{} run job {}", value, Thread.currentThread());
    }

    public Long getValue(){
        return atomicLong.get();
    }

    @Scheduled(cron =  "*/2 * * * * *")
    public void cron(){
        log.info("run cron job");
    }
}
