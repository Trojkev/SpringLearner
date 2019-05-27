package com.kev.Learner.repository;

import com.kev.Learner.entities.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
    @Query(value = "SELECT p FROM Product p WHERE p.id = :id")
    Product getProductByProductId(long id);
}
