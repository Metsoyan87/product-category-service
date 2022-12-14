package com.example.productcategoryservice.dto;

import com.example.productcategoryservice.model.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreatProductDto {


    private String title;
    private int count;
    private double price;
    private Category category;

}
