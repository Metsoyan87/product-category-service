package com.example.productcategoryservice.dto;

import com.example.productcategoryservice.model.Category;
import com.example.productcategoryservice.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EditProductDto {
    private String title;
    private int count;
    private double price;
    private Category category;

}

