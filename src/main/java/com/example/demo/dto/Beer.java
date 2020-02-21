package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Beer {
    private Long id;
    private String type;
    private String name;
    private String alcohol;
    private String volume;
    private Double price;
    private String description;
    private String brewery;
    private Integer stockBalance;
}
