package com.example.demo.service;

import com.example.demo.dto.Message;
import com.example.demo.dto.Status;
import com.example.demo.mapper.OrderMapper;
import com.example.demo.repository.OrderRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public Message updateStatusOrder(final Long orderId, final Status status) {
        orderRepository.findById(orderId).get().setStatus(status);
        return Message.builder().response("Статус изменён на " + orderRepository.findById(orderId).get().getStatus()).build();
    }
}
