package com.example.demo.controller;

import com.example.demo.dto.Message;
import com.example.demo.service.OrderItemService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
    @ApiOperation(value = "Create new order item", notes = "Use this method to create new order")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully create new order item"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    public Message createNewOrderItem(@ApiParam(value = "Beer id", required = true)
                                          @PathVariable final Long beerId,
                                          @ApiParam(value = "Amount beer", required = true)
                                          @PathVariable final Integer amount){
        return orderItemService.createNewOrderItem(beerId, amount);
    }
}
