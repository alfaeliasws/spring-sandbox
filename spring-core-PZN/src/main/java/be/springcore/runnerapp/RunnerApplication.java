package be.springcore.runnerapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//mvn spring-boot:run -- should be only 1 main method in one project
//mvn package -- 1 main method build package for distribution file for production

@SpringBootApplication
public class RunnerApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(RunnerApplication.class, args);
    }

}
