package com.example.demo.controller;

import com.example.demo.service.OrderService;
import lombok.Data;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.*;

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
