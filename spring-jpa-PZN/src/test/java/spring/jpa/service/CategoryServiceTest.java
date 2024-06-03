package spring.jpa.service;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CategoryServiceTest {
    
    @Autowired
    private CategoryService categoryService;
    
    @Autowired
    private CategoryServiceTransactionalOperation categoryServiceTransactionalOperation;

    //Expected transaction failed and data base is not updated because of transaction is failed
    @Test
    void success(){
        assertThrows(RuntimeException.class, () -> {
            categoryService.create();
        } 
        );
    }

    //Should be failed but it is success inserting data because the create() method is called in other methos
    @Test
    void failed(){
        assertThrows(RuntimeException.class, ()-> {
            categoryService.test();
        });
    }

    @Test
    void programmatic(){
        assertThrows(RuntimeException.class, ()-> {
            categoryServiceTransactionalOperation.createCategories();
        });
    }

    @Test
    void manual(){
        assertThrows(RuntimeException.class, ()-> {
            categoryServiceTransactionalOperation.manual();
        });
    }

}
