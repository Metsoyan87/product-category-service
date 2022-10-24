package com.example.productcategoryservice.endpoint;

import com.example.productcategoryservice.model.Category;
import com.example.productcategoryservice.model.Product;
import com.example.productcategoryservice.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class CategoryEndpoint {


    private final CategoryService categoryService;

    @GetMapping("/category")
    public List<Category> getAllCategory() {
        return categoryService.findAllCategory();
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable("id") int id) {
        Optional<Category> byId = categoryService.findCategoryById(id);

        if (byId.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(byId.get());
    }

    @PostMapping("/category")
    public ResponseEntity<?> addCategory(@RequestBody Category category) {
        categoryService.addCategory(category);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/category")
    public ResponseEntity<Category> updateProduct(@RequestBody Category category) {
        if (category.getId() == 0) {
            return ResponseEntity.badRequest().build();
        }
        categoryService.addCategory(category);
        return ResponseEntity.ok(category);
    }

    @DeleteMapping("/category/{id}")
    public ResponseEntity<?> deleteBookById(@PathVariable("id") int id) {
        categoryService.deleteCategoryById(id);
        return ResponseEntity.noContent().build();
    }
}
