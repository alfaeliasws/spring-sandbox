package spring.jpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionOperations;

import spring.jpa.entity.Category;
import spring.jpa.repository.CategoryRepository;

@Service
//for simple async Transaction
public class CategoryServiceTransactionalOperation {

    @Autowired
    private CategoryRepository categoryRepository;

    //for transaction operation
    @Autowired
    private TransactionOperations transactionOperations;

    //for transaction manager
    @Autowired
    private PlatformTransactionManager transactionManager;

    //for more compicated cases use manual transaction manager
    public void manual(){
        DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
        definition.setTimeout(10);
        definition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

        TransactionStatus transaction = transactionManager.getTransaction(definition);

        try {
            for (int i = 0; i < 5; i++) {
                Category category = new Category();
                category.setName("Category " + 1);
                categoryRepository.save(category);
            }
            error();
            transactionManager.commit(transaction);            
        } catch (Throwable throwable) {
            transactionManager.rollback(transaction);
            throw throwable;
        }


    }

    public void error(){
        throw new RuntimeException("Ups");
    }

    //will get executed transactionally even though it is called in another method
    public void createCategories(){
        //executetWithoutResult() often for post that is not returning anything
        //execute() often for get that is returning results
        transactionOperations.executeWithoutResult(transactionStatus -> {
            for (int i = 0; i < 5; i++) {
                Category category = new Category();
                category.setName("Category " + 1);
                categoryRepository.save(category);
            }
            error();
        });
    }




    //If it is called like this, the transactional will not be done because it will make the data get in to database
    public void testProgrammatic(){
        createCategories();
    }

}
