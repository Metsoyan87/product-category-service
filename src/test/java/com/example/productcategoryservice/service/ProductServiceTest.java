package com.example.productcategoryservice.service;

import com.example.productcategoryservice.exception.BadRequestException;
import com.example.productcategoryservice.model.Product;
import com.example.productcategoryservice.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.BDDMockito.given;
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
        Product product = Product.builder()
                .id(1)
                .title("assd")
                .count(7)
                .price(333.33)
                .category(null)
                .build();
        productService.addProduct(product);
        ArgumentCaptor<Product> productArgumentCaptor =
                ArgumentCaptor.forClass(Product.class);
        verify(productRepository)
                .save(productArgumentCaptor.capture());

        Product captorProduct = productArgumentCaptor.getValue();
        assertThat(captorProduct).isEqualTo(product);
    }

    @Test
    void addProductException() {
        int id = 1;
        Product product = Product.builder()
                .id(1)
                .title("assd")
                .count(7)
                .price(333.33)
                .category(null)
                .build();

        given(productRepository.selectExistsEmail(product.getId()))
                .willReturn(true);
        assertThatThrownBy(() -> productService.addProduct(product))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining("Id " + product.getId() + " taken");


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
    void testFindById() {
        Product product = Product.builder()
                .id(1)
                .title("assd")
                .count(7)
                .price(333.33)
                .category(null)
                .build();
        productService.addProduct(product);
        ArgumentCaptor<Product> productArgumentCaptor =
                ArgumentCaptor.forClass(Product.class);
        verify(productRepository)
                .save(productArgumentCaptor.capture());

        Product captorProduct = productArgumentCaptor.getValue();
        assertThat(captorProduct).isEqualTo(product);
    }
}
