package be.springvalidation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import be.springvalidation.properties.DatabaseProperties;

@EnableConfigurationProperties({
	DatabaseProperties.class
})
@SpringBootApplication
public class SpringvalidationApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringvalidationApplication.class, args);
	}

}
