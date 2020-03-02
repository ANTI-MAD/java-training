package com.example.demo.controller;

import com.example.demo.dto.OrderItem;
import com.example.demo.dto.Status;
import com.example.demo.entity.BeerEntity;
import com.example.demo.entity.OrderEntity;
import com.example.demo.entity.OrderItemEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.BeerRepository;
import com.example.demo.security.UserRole;
import com.example.demo.service.BeerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.util.*;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class OrderItemControllerTest extends AbstractControllerTest {

    @Test
    public void testCreateNewOrderItemIsOk() throws Exception {
        final String token = signIn(UserRole.CUSTOMER);
        final OrderItemEntity testOrderItem = getOrderItem();
        given(orderItemRepository.findById(1L)).willReturn(Optional.of(testOrderItem));

        mockMvc.perform(post("/beer-shop-app/orders/1/2").header("Authorization", token)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "      \"beer_id\" : 1,\n" +
                        "      \"order_id\" : 1,\n" +
                        "      \"amount\" : 2\n" +
                        "}"))
                .andExpect(status().isCreated())
                .andExpect(content().json("{\n" +
                        "  \"response\" : \"Beer Maredsous 10° Triple, amount 2\"\n" +
                        "}"));
    }

    private OrderItemEntity getOrderItem() {
        final OrderItemEntity orderItem = new OrderItemEntity();
        orderItem.setId(1L);
        orderItem.setBeer(getBeer());
        orderItem.setAmount(2);
        orderItemRepository.save(orderItem);

        return orderItem;
    }

    private BeerEntity getBeer() {
        final BeerEntity beer = new BeerEntity();
        beer.setId(1L);
        beer.setType("Трипель");
        beer.setName("Maredsous 10° Triple");
        beer.setAlcohol("10.0%");
        beer.setVolume("0.5");
        beer.setPrice(3D);
        beer.setDescription("Бельгийский трипель со слегка сладковатым карамельно-хлебным вкусом, с фруктовыми нотками и пряной хмелевой горчинкой.");
        beer.setBrewery("Abbaye de Maredsous");
        beer.setStockBalance(20);
        beerRepository.save(beer);

        return beer;
    }
}
