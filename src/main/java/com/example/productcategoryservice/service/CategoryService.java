package com.example.productcategoryservice.service;

import com.example.productcategoryservice.dto.CategoryResponseDto;
import com.example.productcategoryservice.dto.EditCategoryDto;
import com.example.productcategoryservice.maper.CategoryMapper;
import com.example.productcategoryservice.model.Category;
import com.example.productcategoryservice.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public List<Category> findAllCategory() {
        return categoryRepository.findAll();
    }

    public Optional<Category> findCategoryById(int id) {
        return categoryRepository.findById(id);
    }

    public void addCategory(Category category) {
        categoryRepository.save(category);
    }

    public void deleteCategoryById(int id) {
        categoryRepository.deleteById(id);
    }

    public List<Category> allCategoryById(int id) {
        return categoryRepository.findCategoryById(id);
    }

    public CategoryResponseDto getById(int id) {
        return categoryMapper.mapToResponseDto(
                categoryRepository.findById(id).orElseThrow(
                        () -> new IllegalStateException("Category not found")));

    }
    public CategoryResponseDto update(int id, EditCategoryDto editCategoryDto) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        Category category = categoryOptional.get();
        if (editCategoryDto.getName() != null) {
            category.setName(editCategoryDto.getName());
        }
        categoryRepository.save(category);

        return categoryMapper.mapToResponseDto(category);
    }

}