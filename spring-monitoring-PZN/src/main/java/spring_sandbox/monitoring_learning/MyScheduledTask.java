package spring_sandbox.monitoring_learning;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import io.micrometer.core.instrument.MeterRegistry;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class MyScheduledTask {
    
    @Autowired
    private MeterRegistry meterRegistry;

    @Scheduled(fixedRate = 10000)
    public void hello(){
        meterRegistry.counter("my.scheduled.task").increment();
        log.info("Hello World");
    }

}
