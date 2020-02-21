package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Customer {
    private Long id;
    private String email;
    private String fio;
    @JsonFormat(pattern="dd.MM.yyyy")
    private LocalDate birthDate;
}