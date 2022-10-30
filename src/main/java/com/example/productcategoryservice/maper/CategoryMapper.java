package com.example.productcategoryservice.maper;

import com.example.productcategoryservice.dto.CategoryResponseDto;
import com.example.productcategoryservice.dto.CreatCategoryDto;
import com.example.productcategoryservice.model.Category;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")

public interface CategoryMapper {

    Category mapToEntity(CreatCategoryDto creatCategoryDto);

    CategoryResponseDto mapToResponseDto(Category category);

    List<CategoryResponseDto> mapToResponseDtoList(List<Category> categoryList);
}
