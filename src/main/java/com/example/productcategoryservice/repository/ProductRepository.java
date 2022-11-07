package com.example.productcategoryservice.repository;

import com.example.productcategoryservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    Boolean selectExistsEmail(int id);
}
