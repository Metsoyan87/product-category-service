package com.example.productcategoryservice.maper;

import com.example.productcategoryservice.dto.CategoryResponseDto;
import com.example.productcategoryservice.dto.CreatCategoryDto;
import com.example.productcategoryservice.dto.CreatProductDto;
import com.example.productcategoryservice.dto.ProductResponseDto;
import com.example.productcategoryservice.model.Category;
import com.example.productcategoryservice.model.Product;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")

public interface CategoryMapper {

    Product map(CreatCategoryDto creatCategoryDto);

    CategoryResponseDto map(Category category);

    List<CategoryResponseDto> map(List<Category> categoryList);
}
