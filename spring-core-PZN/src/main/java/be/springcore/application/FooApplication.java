package be.springcore.application;

import java.util.List;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import be.springcore.data.Bar;
import be.springcore.data.Foo;
import be.springcore.listener.AppStartingListener;

@SpringBootApplication
public class FooApplication {
    
    @Bean
    public Foo foo(){
        return new Foo();
    }

    // public static void main(String[] args) {
        
    //     ConfigurableApplicationContext applicationContext = SpringApplication.run(FooApplication.class, args);

    //     Foo foo = applicationContext.getBean(Foo.class);
    //     System.out.println(foo);

    // }

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(FooApplication.class);
        application.setBannerMode(Banner.Mode.OFF);
        application.setListeners(List.of(new AppStartingListener()));

        ConfigurableApplicationContext applicationContext = application.run(args);

        applicationContext.getBean(Foo.class);
    }

}
