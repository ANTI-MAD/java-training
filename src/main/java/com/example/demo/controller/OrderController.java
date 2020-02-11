package com.example.demo.controller;

import com.example.demo.dto.Order;
import com.example.demo.service.OrderService;
import lombok.Data;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Log
@Data
@RestController
@RequestMapping("/beerShop-app/beer")
public class OrderController {
    private final OrderService orderService;

    @PatchMapping(value = "/admin/orders/{orderId}")
    @ResponseStatus(HttpStatus.OK)
    public String updateOrder(@PathVariable final Long orderId) {
        return orderService.updateOrder(orderId);
    }
}
