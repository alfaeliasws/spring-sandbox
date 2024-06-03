package spring.jpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
// import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import spring.jpa.entity.Category;
import spring.jpa.repository.CategoryRepository;

    //propagation attribute
    // @Transactional(propagation = Propagation.REQUIRED) --default
    // 0. Propagation.REQUIRED = The method can be called by other method even though it is not having the Transactional annotation, it will create the Transaction automatically
    // @Transactional(propagation = Propagation.MANDATORY)
    // 1. Propagation.MANDATORY = The method should be called by Transactional annotation also (even in test), if not it will not run the transaction
    // @Transactional(propagation = Propagation.REQUIRE_NEW)
    // 2. Propagation.REQUIRE_NEW = The method can be called by other method even though it is not having the Transactional annotation, it will create the Transaction automatically but it will suspend the running Transaction if exists
    // @Transactional(propagation = Propagation.NEVER)
    // 3. Propagation.NEVER = Will not execute Transaction, will only do the query just like it is. Will throw error if there is transaction.
    // @Transactional(propagation = Propagation.NOT_SUPPORTED)
    // 4. Propagation.NOT_SUPPORTED = Will not execute Transaction, will only do the query just like it is. Will stop Transactions if exists
    // @Transactional(propagation = Propagation.NESTED)
    // 5. Propagation.NESTED = Will execute Transaction in nested Transaction if current Transaction exists
    // @Transactional(propagation = Propagation.SUPPORTS)
    // 5. Propagation.NESTED = Will execeute Transaction, execute non-transactionally if none exists


//make sure to call the direct method that is having Transactional annotation (if the annotation doesn't have propagation)
@Service
public class CategoryService {

    //should not be inserting data to DB because of runtime exception (Transaction not success)
    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional
    public void create(){
        for(int i = 0; i < 5; i++){
            Category category = new Category();
            category.setName("Category " + i);

            categoryRepository.save(category);
        }

        throw new RuntimeException("Ups rollback please");
    }

    //If it is called like this, the transactional will not be done because it will make the data get in to database
    public void test(){
        create();
    }
}
