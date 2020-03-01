package com.example.demo.controller;

import com.example.demo.dto.Message;
import com.example.demo.dto.Status;
import com.example.demo.service.OrderService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.Data;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Log
@Data
@RestController
@RequestMapping("/beer-shop-app")
public class OrderController {
    private final OrderService orderService;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/admin/orders/{orderId}/{status}")
    @ApiOperation(value = "Update status order", notes = "Use this method to update status order")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully update status"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    public Message updateStatusOrder(@ApiParam(value = "Order id", required = true)
                                         @PathVariable final Long orderId,
                                         @ApiParam(value = "Order status", required = true)
                                         @PathVariable final Status status) {
        return orderService.updateStatusOrder(orderId, status);
    }
}