package com.example.demo.service;

import com.example.demo.dto.Message;
import com.example.demo.dto.OrderItem;
import com.example.demo.mapper.OrderItemMapper;
import com.example.demo.repository.OrderItemRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;
    private final OrderItemMapper orderItemMapper;

    public Message createNewOrderItem(final Long beerId, final Integer amount) {
        orderItemRepository.save(orderItemMapper.sourceToDestination(OrderItem.builder()
                .id(1L)
                .beerId(beerId)
                .amount(amount)
                .orderId(1L)
                .build()));
        return Message.builder().response("Beer " + orderItemRepository.findById(1L).get().getBeer().getName() +
                ", amount " + orderItemRepository.findById(1L).get().getAmount()).build();
    }
}
