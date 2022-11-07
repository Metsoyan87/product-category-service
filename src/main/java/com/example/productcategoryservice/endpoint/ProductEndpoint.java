package com.example.productcategoryservice.endpoint;

import com.example.productcategoryservice.dto.EditProductDto;
import com.example.productcategoryservice.dto.ProductResponseDto;
import com.example.productcategoryservice.model.Product;
import com.example.productcategoryservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class ProductEndpoint {

    private final ProductService productService;

    @GetMapping("/product")
    public List<Product> getAllProduct() {
        return productService.findAllProducts();
    }

    @GetMapping("/id")
    public ResponseEntity<Product> getProductById( int id) {
        Optional<Product> byId = productService.findById(id);
        if (byId.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(byId.get());
    }

    @PostMapping("/product/add")
    public ResponseEntity<?> addProduct(@RequestBody Product product) {
        productService.addProduct(product);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/product/id")
    public ResponseEntity<ProductResponseDto> updateProduct(@RequestBody EditProductDto editProductDto,int id) {
        return ResponseEntity.ok(productService.update(editProductDto,id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBookById(@PathVariable("id") int id) {
        productService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
