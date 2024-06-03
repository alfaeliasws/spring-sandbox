package be.springcore;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages =  {
     "be.springcore.configuration"
})
public class ScanConfiguration {
    
}
