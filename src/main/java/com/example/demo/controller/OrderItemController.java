package com.example.demo.controller;

import com.example.demo.dto.Message;
import com.example.demo.service.OrderItemService;
import lombok.Data;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Log
@Data
@RestController
@RequestMapping("/beer-shop-app")
public class OrderItemController {
    private final OrderItemService orderItemService;

    @PostMapping(value = "/orders/{beerId}/{amount}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Message addNewBeer(@PathVariable final Long beerId, @PathVariable final Integer amount){
        return orderItemService.createNewOrderItem(beerId, amount);
    }
}
