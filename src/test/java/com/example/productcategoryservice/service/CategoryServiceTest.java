package com.example.productcategoryservice.service;

import com.example.productcategoryservice.model.Category;
import com.example.productcategoryservice.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.verify;


@SpringBootTest
class CategoryServiceTest {
    @MockBean  // moc anel imitacia anel vor ka baza
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryService categoryService;

    @Test
    void findAllCategory() {
        categoryService.findAllCategory();
        verify(categoryRepository).findAll();
    }

    @Test
    void findCategoryById() {
        Category category = Category.builder()
                .id(1)
                .name("asasas")
                .build();
        Optional<Category> byId = categoryRepository.findById(category.getId());
        assertFalse(byId.isPresent());
    }

    @Test
    void addCategory() {
        Category category = Category.builder()
                .id(1)
                .name("asasas")
                .build();
        categoryService.addCategory(category);
        ArgumentCaptor<Category> categoryArgumentCaptor =
                ArgumentCaptor.forClass(Category.class);
        verify(categoryRepository)
                .save(categoryArgumentCaptor.capture());

        Category captorCategory = categoryArgumentCaptor.getValue();
        assertThat(captorCategory).isEqualTo(category);
    }

    @Test
    void deleteCategoryById() {
        Category category = Category.builder()
                .id(1)
                .name("asasas")
                .build();
        categoryService.addCategory(category);
        categoryService.deleteCategoryById(category.getId());
        Optional<Category> byId=categoryRepository.findById(category.getId());
        assertFalse(byId.isPresent());
    }

    @Test
    void getById() {
        Category category = Category.builder()
                .id(1)
                .name("asasas")
                .build();
        categoryService.addCategory(category);
        ArgumentCaptor<Category> categoryArgumentCaptor =
                ArgumentCaptor.forClass(Category.class);
        verify(categoryRepository)
                .save(categoryArgumentCaptor.capture());

        Category captorCategory = categoryArgumentCaptor.getValue();
        assertThat(captorCategory).isEqualTo(category);
    }

}