package com.example.demo.controller;

import com.example.demo.dto.Message;
import com.example.demo.dto.Status;
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

    @PostMapping(value = "/admin/orders/{orderId}/{status}")
    public Message updateStatusOrder(@PathVariable final Long orderId, @PathVariable final Status status) {
        return orderService.updateStatusOrder(orderId, status);
    }
}