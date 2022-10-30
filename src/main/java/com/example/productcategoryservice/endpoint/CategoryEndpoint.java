package com.example.productcategoryservice.endpoint;

import com.example.productcategoryservice.dto.CategoryResponseDto;
import com.example.productcategoryservice.dto.EditCategoryDto;
import com.example.productcategoryservice.maper.CategoryMapper;
import com.example.productcategoryservice.model.Category;
import com.example.productcategoryservice.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")

public class CategoryEndpoint {
    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @GetMapping
    public List<CategoryResponseDto> getAllCategory() {
        return categoryMapper.mapToResponseDtoList(categoryService.findAllCategory());
    }

    @GetMapping("/byCategory/{id}")
    public List<CategoryResponseDto> allByCategory(@PathVariable("id") int id) {
        return categoryMapper.mapToResponseDtoList(categoryService.allCategoryById(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDto> getCategoryById(@PathVariable("id") int id) {
        return ResponseEntity.ok(categoryService.getById(id));//u gre es dzevov o
    }

    @PostMapping
    public ResponseEntity<?> addCategory(@RequestBody Category category) {
        categoryService.addCategory(category);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponseDto> update(@PathVariable("id") int id,
                                                      @RequestBody EditCategoryDto editCategoryDto) {
        return ResponseEntity.ok(categoryService.update(id, editCategoryDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBookById(@PathVariable("id") int id) {
        categoryService.deleteCategoryById(id);
        return ResponseEntity.noContent().build();
    }

}
