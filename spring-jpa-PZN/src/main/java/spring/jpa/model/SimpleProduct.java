package spring.jpa.model;

//For data that is simpler than the actual data and it can be the generic type of object that can be get from repository method
public interface SimpleProduct {

    Long getId();

    String getName();

}
