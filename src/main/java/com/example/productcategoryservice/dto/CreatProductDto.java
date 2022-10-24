package com.example.productcategoryservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatProductDto {

    private int id;
    private String title;
    private int count;
    private double price;

}
