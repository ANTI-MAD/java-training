package com.example.demo.dto;

import lombok.Data;

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
