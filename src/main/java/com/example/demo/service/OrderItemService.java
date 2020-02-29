package com.example.demo.service;

import com.example.demo.dto.Message;
import com.example.demo.dto.OrderItem;
import com.example.demo.mapper.OrderItemMapper;
import com.example.demo.repository.OrderItemRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@Data
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;
    private final OrderItemMapper orderItemMapper;
    private final BeerService beerService;

    /*@PostConstruct
    public void init() {
        //beerService.init();
        orderItemRepository.save(orderItemMapper.sourceToDestination(OrderItem.builder()
                .id(1L)
                .beerId(1L)
                //.orderId(1L)
                .amount(1)
                .build()));
    }*/

    public Message createNewOrderItem(final Long beerId) {
        //beerService.init();
        orderItemRepository.save(orderItemMapper.sourceToDestination(OrderItem.builder()
                .id(1L)
                .beerId(1L)
                .amount(2)
                .build()));
        return Message.builder().response("Beer " + orderItemRepository.findById(1L).get().getBeer().getName() +
                ", amount " + orderItemRepository.findById(1L).get().getAmount()).build();
    }
}
