package com.example.demo.controller;

import com.example.demo.service.OrderService;
import lombok.Data;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log
@Data
@RestController
@RequestMapping("/beer-shop-app")
public class OrderController {
    private final OrderService orderService;

    @PutMapping(value = "/admin/orders/{orderId}")
    public String updateStatusOrder(@PathVariable final Long orderId) {
        return orderService.updateStatusOrder(orderId);
    }
}