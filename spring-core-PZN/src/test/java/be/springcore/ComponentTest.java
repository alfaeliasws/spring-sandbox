package be.springcore;

import java.util.Locale.Category;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import be.springcore.data.MultiFoo;
import be.springcore.repository.CategoryRepository;
import be.springcore.repository.CustomerRepository;
import be.springcore.repository.ProductRepository;
import be.springcore.service.CategoryService;
import be.springcore.service.CustomerService;
import be.springcore.service.ProductService;
import lombok.extern.slf4j.Slf4j;

public class ComponentTest {
    
    private ConfigurableApplicationContext applicationContext;

    @BeforeEach
    void setUp(){
        applicationContext = new AnnotationConfigApplicationContext(ComponentConfiguration.class);
        applicationContext.registerShutdownHook();
    }

    @Test
    void testService(){

        ProductService productService1 = applicationContext.getBean(ProductService.class);
        ProductService productService2 = applicationContext.getBean("productService", ProductService.class);

        Assertions.assertSame(productService1, productService2);

    }

    @Test
    void testConstructorDependencyInjection(){
        ProductService productService = applicationContext.getBean(ProductService.class);
        ProductRepository productRepository = applicationContext.getBean(ProductRepository.class);

        Assertions.assertSame(productRepository, productService.getProductRepository());
    }

    @Test
    void testSetterDependencyInjection(){
        
        CategoryService categoryService = applicationContext.getBean(CategoryService.class);
        CategoryRepository categoryRepository = applicationContext.getBean(CategoryRepository.class);
        
        Assertions.assertSame(categoryService.getCategoryRepository(), categoryRepository);

    }

    // @Test
    // void testFieldDependencyInjection(){
        
    //     CustomerService customerService = applicationContext.getBean(CustomerService.class);
    //     CustomerRepository customerRepository = applicationContext.getBean(CustomerRepository.class);
    
    //     Assertions.assertSame(customerService.getCustomerRepository(), customerRepository);

    // }
    
    @Test
    void testFieldDependencyInjection(){
        

        CustomerService customerService = applicationContext.getBean(CustomerService.class);
        CustomerRepository normalCustomerRepository = applicationContext.getBean( "normalCustomerRepository" ,CustomerRepository.class);
        CustomerRepository premCustomerRepository = applicationContext.getBean( "premiumCustomerRepository" , CustomerRepository.class);

        Assertions.assertSame(customerService.getNormalCustomerRepository(), normalCustomerRepository);
        Assertions.assertSame(customerService.getPremiumCustomerRepository(), premCustomerRepository);

    }

    // @Test
    // void testObjectProvider(){

    //     MultiFoo multiFoo = applicationContext.getBean(MultiFoo.class);
    //     Assertions.assertEquals(3, multiFoo.getFoos().size());
    // }

    

}
