package com.example.productcategoryservice.endpoint;

import com.example.productcategoryservice.model.Category;
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

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") int id) {
        Optional<Product> byId = productService.findById(id);

        if (byId.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(byId.get());
    }

    @PostMapping("/product")
    public ResponseEntity<?> addProduct(@RequestBody Product product) {
        productService.addProduct(product);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/product")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
        if (product.getId() == 0) {
            return ResponseEntity.badRequest().build();
        }
        productService.addProduct(product);
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<?> deleteBookById(@PathVariable("id") int id) {
        productService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
