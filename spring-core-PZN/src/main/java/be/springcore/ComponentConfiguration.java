package be.springcore;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import be.springcore.data.MultiFoo;

@Configuration
@ComponentScan(basePackages = {
    "be.springcore.service",
    "be.springcore.repository",
    "be.springcore.configuration"
})
@Import(MultiFoo.class)
public class ComponentConfiguration {
    
}
