package com.example.productcategoryservice.service;

import com.example.productcategoryservice.model.Product;
import com.example.productcategoryservice.model.Role;
import com.example.productcategoryservice.model.User;
import com.example.productcategoryservice.repository.ProductRepository;
import com.example.productcategoryservice.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

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
        Product product = Product.builder()
                .id(1)
                .title("assd")
                .count(7)
                .price(333.33)
                .category(null)
                .build();
        Optional<Product> byId = productRepository.findById(product.getId());
        assertFalse(byId.isPresent());
    }

    @Test
    void addProduct() {

    }

    @Test
    void deleteById() {
        Product product = Product.builder()
                .id(1)
                .title("assd")
                .count(7)
                .price(333.33)
                .category(null)
                .build();
        productService.addProduct(product);
        productService.deleteById(product.getId());
        Optional<Product> byId = productRepository.findById(product.getId());
        assertFalse(byId.isPresent());
    }



    @Test
    void update() {
    }
}