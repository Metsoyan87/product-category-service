package com.example.productcategoryservice.service;

import com.example.productcategoryservice.dto.EditProductDto;
import com.example.productcategoryservice.dto.ProductResponseDto;
import com.example.productcategoryservice.maper.ProductMapper;
import com.example.productcategoryservice.model.Product;
import com.example.productcategoryservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> findById(int id) {
        return productRepository.findById(id);
    }

    public void addProduct(Product product) {
        productRepository.save(product);
    }

    public void deleteById(int id) {
        productRepository.deleteById(id);
    }
    public ProductResponseDto update(EditProductDto editProductDto,int id) {
        Optional<Product> productOptional = productRepository.findById(id);

        Product product = productOptional.get();

        product.setTitle(editProductDto.getTitle());

        product.setPrice(editProductDto.getPrice());

        product.setCategory(editProductDto.getCategory());

        productRepository.save(product);

        return productMapper.map(product);
    }

}
