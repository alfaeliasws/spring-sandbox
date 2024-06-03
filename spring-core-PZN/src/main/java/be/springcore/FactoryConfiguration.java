package be.springcore;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import be.springcore.factory.PaymentGatewayClientFactoryBean;

@Configuration
@Import({PaymentGatewayClientFactoryBean.class})
public class FactoryConfiguration {
    
}
