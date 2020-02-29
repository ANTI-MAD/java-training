package com.example.demo.mapper;

import com.example.demo.dto.OrderItem;
import com.example.demo.entity.OrderItemEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {
    OrderItemEntity sourceToDestination(OrderItem source);
    OrderItem destinationToSource(OrderItemEntity destination);
}
