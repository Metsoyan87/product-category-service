package com.example.productcategoryservice.service;

import com.example.productcategoryservice.repository.ProductRepository;
import com.example.productcategoryservice.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@SpringBootTest
class ProductServiceTest {

    @MockBean  // moc anel imitacia anel vor ka baza
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    @Test
    void findAllProducts() {
        productService.findAllProducts();
        verify(productRepository).findAll();
    }

    @Test
    void findById() {
    }

    @Test
    void addProduct() {
    }

    @Test
    void deleteById() {
    }

    @Test
    void update() {
    }
}