package com.example.productcategoryservice.maper;


import com.example.productcategoryservice.dto.CreatProductDto;
import com.example.productcategoryservice.dto.ProductResponseDto;
import com.example.productcategoryservice.model.Product;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")

public interface ProductMapper {

    Product map(CreatProductDto creatProductDto);

    ProductResponseDto map(Product product);

    List<ProductResponseDto> map(List<Product> productList);
}
