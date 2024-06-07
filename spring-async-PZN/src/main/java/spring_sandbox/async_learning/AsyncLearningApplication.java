package spring_sandbox.async_learning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableAsync
@EnableScheduling
public class AsyncLearningApplication {

	public static void main(String[] args) {
		SpringApplication.run(AsyncLearningApplication.class, args);
	}

}
