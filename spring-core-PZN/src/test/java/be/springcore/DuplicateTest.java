package be.springcore;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import be.springcore.data.Foo;

public class DuplicateTest {

    @Test
    void testDuplicate(){

        ApplicationContext context = new AnnotationConfigApplicationContext(DuplicateConifguration.class);
    
        Assertions.assertThrows(NoUniqueBeanDefinitionException.class, () -> {
            Foo foo = context.getBean(Foo.class);
        });

    }

    @Test
    void testGetBean(){

        ApplicationContext context = new AnnotationConfigApplicationContext(DuplicateConifguration.class);

        Foo foo1 = context.getBean("foo1", Foo.class);
        Foo foo2 = context.getBean("foo2", Foo.class);

        Assertions.assertNotSame(foo1, foo2);

    }


}
