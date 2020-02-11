package com.example.demo.service;

import com.example.demo.dto.Order;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    public String updateOrder(final Long orderId) {
        return "{\"id\":1}";
    }
}
