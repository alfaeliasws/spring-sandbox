package be.springaop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
@SpringBootApplication
public class LearnSpringAopApplication {

	public static void main(String[] args) {
		SpringApplication.run(LearnSpringAopApplication.class, args);
	}

}
