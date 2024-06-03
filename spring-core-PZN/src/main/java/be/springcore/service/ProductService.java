package be.springcore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import be.springcore.repository.ProductRepository;
import lombok.Getter;

// @Scope("prototype")
// @Lazy
@Component
public class ProductService {
    
    @Getter
    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    } 

    public ProductService(ProductRepository productRepository, String name){
        this.productRepository = productRepository;
    } 
}
