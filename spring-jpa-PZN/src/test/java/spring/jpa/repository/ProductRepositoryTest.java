package spring.jpa.repository;
import org.hibernate.boot.beanvalidation.IntegrationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.support.TransactionOperations;

import spring.jpa.entity.Category;
import spring.jpa.entity.Product;
import spring.jpa.model.ProductPrice;
import spring.jpa.model.SimpleProduct;
import spring.jpa.model.SimpleProductRecord;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.stream.Stream;

@SpringBootTest
public class ProductRepositoryTest {
    
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TransactionOperations transactionOperations;
    
    @Test
    void createProduct(){
        Category category = categoryRepository.findById(1L).orElse(null);
        assertNotNull(category);

        {
            Product product = new Product();
            product.setName("Apple iPhone 14 Pro Max");
            product.setPrice(25_000_000L);
            product.setCategory(category);
            productRepository.save(product);
        }

        {
            Product product = new Product();
            product.setName("Apple iPhone 13 Pro Max");
            product.setPrice(18_000_000L);
            product.setCategory(category);
            productRepository.save(product);
        }
    }

    @Test
    void findByCategoryName(){
        List<Product> products = productRepository.findAllByCategory_Name("GADGET MURAH");
        assertEquals(2, products.size());
        assertEquals("Apple iPhone 14 Pro Max", products.get(0).getName());
        assertEquals("Apple iPhone 13 Pro Max", products.get(1).getName());
    }

    @Test
    void findProductSort(){
        Sort sort = Sort.by(Sort.Order.desc("id"));

        List<Product> products = productRepository.findAllByCategory_Name("GADGET MURAH", sort);

        assertEquals(2, products.size());

        assertEquals("Apple iPhone 14 Pro Max", products.get(1).getName());
        assertEquals("Apple iPhone 13 Pro Max", products.get(0).getName());

    }

    @Test
    void testFindProductWithPageable(){
        //page starts with 0, first parameter is what page (starts from 0 like index), second parameter is the size of data that should be able to be get
        
        // page 0
        Pageable pageable = PageRequest.of(0, 1, Sort.by(Sort.Order.desc("id")));
        // List<Product> products = productRepository.findAllByCategory_Name("GADGET MURAH", pageable);
        Page<Product> products = productRepository.findAllByCategory_Name("GADGET MURAH", pageable);


        // assertEquals(1, products.size());
        // assertEquals("Apple iPhone 13 Pro Max", products.get(0).getName());

        assertEquals(1, products.getContent().size()); // take the content (List) of the data that is taken
        assertEquals(0, products.getNumber()); // on what page
        assertEquals(2, products.getTotalElements()); // how many elements on all pages that is get
        assertEquals(2, products.getTotalPages()); // how many pages that is get
        assertEquals("Apple iPhone 13 Pro Max", products.getContent().get(0).getName());

        // page 1
        pageable = PageRequest.of(1, 1, Sort.by(Sort.Order.desc("id")));
        // products = productRepository.findAllByCategory_Name("GADGET MURAH", pageable);
        products = productRepository.findAllByCategory_Name("GADGET MURAH", pageable);

        // assertEquals(1, products.size());
        // assertEquals("Apple iPhone 14 Pro Max", products.get(0).getName());

        assertEquals(1, products.getContent().size()); // take the content (List) of the data that is taken
        assertEquals(1, products.getNumber()); // on what page
        assertEquals(2, products.getTotalElements()); // how many element on the current page
        assertEquals(2, products.getTotalPages()); // how many pages that is get
        assertEquals("Apple iPhone 14 Pro Max", products.getContent().get(0).getName());

    }

    @Test
    void testCount(){
        Long count = productRepository.count();
        assertEquals(2L, count);

        count = productRepository.countByCategory_Name("GADGET MURAH");
        assertEquals(2L, count);

        count = productRepository.countByCategory_Name("GAK ADA");
        assertEquals(0L, count);
    }

    @Test
    void testExists(){
        boolean exists = productRepository.existsByName("Apple iPhone 14 Pro Max");
        assertTrue(exists);

        exists = productRepository.existsByName("Apple iPhone 15 Pro Max");
        assertFalse(false);

    }

    @Test
    void testDeleteProgrammatic(){
        //all in this block is in 1 transaction, rollback happen when within this block of method something is not working
        transactionOperations.executeWithoutResult(transactionStatus -> {
            Category category = categoryRepository.findById(1L).orElse(null);
            assertNotNull(category);

            Product product = new Product();
            product.setName("Samsung Galaxy S9");
            product.setPrice(10_000_000L);
            product.setCategory(category);
            productRepository.save(product);

            // int delete = productRepository.deleteByNameProgrammatic("Samsung Galaxy S9");
            // assertEquals(1, delete);

            // delete = productRepository.deleteByNameProgrammatic("Samsung Galaxy S9");
            // assertEquals(0, delete);
        });
    }

    @Test
    void testDeleteDeclarative(){
            //all in this block is transactional separately line by line, rollback won't happen to all the input but to the one line that is not working (previous line still manipulates data in DB)
            Category category = categoryRepository.findById(1L).orElse(null);
            assertNotNull(category);

            Product product = new Product();
            product.setName("Samsung Galaxy S10");
            product.setPrice(10_000_000L);
            product.setCategory(category);
            productRepository.save(product);

            int delete = productRepository.deleteByName("Samsung Galaxy S10");
            assertEquals(1, delete);

            delete = productRepository.deleteByName("Samsung Galaxy S10");
            assertEquals(0, delete);
    }

    @Test
    void searchProduct(){
        List<Product> products = productRepository.searchProductUsingNameNoPageable("Apple iPhone 14 Pro Max");
        assertEquals(1L, products.size());
        assertEquals("Apple iPhone 14 Pro Max", products.get(0).getName()); 
    }

    @Test
    void searchProductPageable(){
        Pageable pageable = PageRequest.of(0, 1);
        List<Product> products = productRepository.searchProductUsingName("Apple iPhone 14 Pro Max", pageable);
        assertEquals(1L, products.size());
        assertEquals("Apple iPhone 14 Pro Max", products.get(0).getName()); 
    }
    
    @Test
    void searchProductLike(){
        List<Product> products = productRepository.searchProduct("%iPhone%");
        assertEquals(2, products.size());

        products = productRepository.searchProduct("%GADGET%");
        assertEquals(2, products.size());
    }

    @Test
    void searchProductLikePageableWithList(){
        Pageable pageable = PageRequest.of(0, 1, Sort.by(Sort.Order.desc("id")));
        List<Product> products = productRepository.searchProduct("%iPhone%", pageable);
        assertEquals(1, products.size());

        products = productRepository.searchProduct("%GADGET%", pageable);
        assertEquals(1, products.size());
    }

    @Test
    void searchProductLikePageableWithPage(){
        Pageable pageable = PageRequest.of(0, 1, Sort.by(Sort.Order.desc("id")));
        Page<Product> products = productRepository.searchProductWithCount("%iPhone%", pageable);
        assertEquals(1, products.getContent().size());
        assertEquals(0, products.getNumber());
        assertEquals(2, products.getTotalPages());
        assertEquals(2, products.getTotalElements());

        products = productRepository.searchProductWithCount("%GADGET%", pageable);
        assertEquals(1, products.getContent().size());
        assertEquals(0, products.getNumber());
        assertEquals(2, products.getTotalPages());
        assertEquals(2, products.getTotalElements());
    }

    @Test
    void modifying(){
        //all in this block is in 1 transaction, rollback happen when within this block of method something is not working
        transactionOperations.executeWithoutResult(transactionStatus -> {
            int total = productRepository.deleteProductUsingName("Wrong");
            assertEquals(0, total);

            total = productRepository.updateProductPriceToZero(1L);
            assertEquals(1, total);

            Product product = productRepository.findById(1L).orElse(null);
            assertNotNull(product);
            assertEquals(0L, product.getPrice());
        });
    }

    @Test
    void stream(){
        transactionOperations.executeWithoutResult(transactionStatus -> {
            Category category = categoryRepository.findById(1L).orElse(null);
            assertNotNull(category);
    
            Stream<Product> stream = productRepository.streamAllByCategory(category);
            stream.forEach(product -> System.out.println(product.getId() + " : " + product.getName()));
        });
    }

    @Test
    void slice() {
        Pageable pageable = PageRequest.of(0, 1);

        Category category = categoryRepository.findById(1L).orElse(null);
        assertNotNull(category);

        Slice<Product> slice = productRepository.findAllByCategory(category, pageable);
        
        while(slice.hasNext()){
            slice = productRepository.findAllByCategory(category, slice.nextPageable());
        }
    }

    @Test
    void lock1(){
        transactionOperations.executeWithoutResult(transactionStatus -> {
            try {
                Product product = productRepository.findFirstByIdEquals(1L).orElse(null);
                assertNotNull(product);
                product.setPrice(30_000_000L);

                Thread.sleep(20_000L);
                productRepository.save(product);
            } catch (InterruptedException exception) {
                throw new RuntimeException();
            }
        });
    }

    @Test
    void lock2(){
        transactionOperations.executeWithoutResult(transactionStatus -> {
            Product product = productRepository.findFirstByIdEquals(1L).orElse(null);
            assertNotNull(product);
            product.setPrice(10_000_000L);
            productRepository.save(product);
        });
    }

    @Test
    void specification(){
        //to make more advance database test case --for dynamic data
        Specification<Product> specification = (root, criteriaQuery, criteriaBuilder) -> {
            return criteriaQuery.where(
                criteriaBuilder.or(
                    criteriaBuilder.equal(root.get("name"), "Apple iPhone 14 Pro Max"),
                    criteriaBuilder.equal(root.get("name"), "Apple iPhone 13 Pro Max")
                )
            ).getRestriction();
        };

        List<Product> products = productRepository.findAll(specification);
        assertEquals(2, products.size());
    }

    @Test
    void projection(){
        List<SimpleProduct> simpleProducts = productRepository.findAllByNameLike("%Apple%", SimpleProduct.class);
        assertEquals(2, simpleProducts.size());
    }

    @Test
    void projectionRecord(){
        List<SimpleProductRecord> simpleProducts = productRepository.findAllByNameLike("%Apple%");
        assertEquals(2, simpleProducts.size());
    }

    @Test
    void dynamicProjection(){
        List<SimpleProduct> simpleProducts = productRepository.findAllByNameLike("%Apple%", SimpleProduct.class);
        assertEquals(2, simpleProducts.size());

        List<ProductPrice> productPrices = productRepository.findAllByNameLike("%Apple%", ProductPrice.class);
        assertEquals(2, productPrices.size());

    }
}
