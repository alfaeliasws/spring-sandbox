package spring.jpa.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

import spring.jpa.entity.Category;

@SpringBootTest
public class CategoryRepositoryTest {
    
    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void insert(){
        Category category = new Category();
        category.setName("GADGET");

        categoryRepository.save(category);
        
        assertNotNull(category.getId());

    }

    @Test
    void update(){
        Category category = categoryRepository.findById(1L).orElse(null);

        category.setName("GADGET MURAH");
        categoryRepository.save(category);

        // category = categoryRepository.delete();

        category = categoryRepository.findById(1L).orElse(null);
        assertNotNull(category);
        assertEquals("GADGET MURAH", category.getName());

    }

    @Test
    void queryMethod(){
        Category category = categoryRepository.findFirstByNameEquals("GADGET MURAH").orElse(null);
        assertNotNull(category);
        assertEquals("GADGET MURAH", category.getName());

        List<Category> categories = categoryRepository.findAllByNameLike("%GADGET%");
        assertEquals(1, categories.size());
        assertEquals("GADGET MURAH", categories.get(0).getName());

    }

    @Test
    void audit(){
        Category category = new Category();
        category.setName("Sample Audit");
        categoryRepository.save(category);

        assertNotNull(category.getId());
        assertNotNull(category.getCreatedDate());
        assertNotNull(category.getLastModifieldDate());
    }

    @Test
    void example(){
        Category category = new Category();
        category.setName("GADGET MURAH");

        Example<Category> example = Example.of(category);

        List<Category> categories = categoryRepository.findAll(example);
        assertEquals(1, categories.size());

    }

    @Test
    void example2(){
        Category category = new Category();
        category.setId(1L);

        //Getting data that is set in category (not really inserting data) -- simple way to test
        //Getting rid of this to have data to be tested: Category category = categoryRepository.findFirstByNameEquals("GADGET MURAH").orElse(null);
        Example<Category> example = Example.of(category);

        List<Category> categories = categoryRepository.findAll(example);
        assertEquals(1, categories.size());

    }
    
    @Test
    void exampleMatcher(){
        Category category = new Category();
        category.setName("gadget murah");

        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreNullValues().withIgnoreCase();
        
        Example<Category> example = Example.of(category, matcher);
        List<Category> categories = categoryRepository.findAll(example);

        assertEquals(1, categories.size());

    }

}
