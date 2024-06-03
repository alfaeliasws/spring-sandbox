package be.springcore;

import org.springframework.context.annotation.Import;

import be.springcore.service.MerchantServiceImpl;

@Import(MerchantServiceImpl.class)
public class InheritanceConfiguration {


}
