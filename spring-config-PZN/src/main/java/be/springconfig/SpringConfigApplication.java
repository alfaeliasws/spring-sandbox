package be.springconfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.convert.ApplicationConversionService;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.ConversionService;

import be.springconfig.converter.StringToDateConverter;
import be.springconfig.properties.ApplicationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
	ApplicationProperties.class
})
public class SpringConfigApplication {

	@Bean
	public ConversionService conversionService(StringToDateConverter stringToDateConverter){
		ApplicationConversionService conversionService = new ApplicationConversionService();
		conversionService.addConverter(stringToDateConverter);
		return conversionService;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringConfigApplication.class, args);
	}

}
