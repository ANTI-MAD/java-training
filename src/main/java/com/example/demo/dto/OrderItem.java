package com.example.demo.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderItem {
    private Long id;
    private Long beerId;
    //private Long orderId;
    private Integer amount;
}
