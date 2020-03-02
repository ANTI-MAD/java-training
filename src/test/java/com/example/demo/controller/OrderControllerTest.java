package com.example.demo.controller;

import com.example.demo.dto.Status;
import com.example.demo.entity.OrderEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.security.UserRole;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class OrderControllerTest extends AbstractControllerTest {

    @Test
    public void testUpdateStatusOrder() throws Exception {
        final String token = signIn(UserRole.ADMIN);
        final OrderEntity testOrder = getOrder();
        given(orderRepository.findById(1L)).willReturn(Optional.of(testOrder));

        mockMvc.perform(post("/beer-shop-app/admin/orders/1/Processed").header("Authorization", token)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"processed\": true\n" +
                        "}"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\n" +
                        "    \"response\": \"Статус изменён на Processed\"\n" +
                        "}"));

    }

    @Test
    public void testUpdateStatusOrderDeniedForClient() throws Exception {
        final String token = signIn(UserRole.CUSTOMER);

        mockMvc.perform(post("/beer-shop-app/admin/orders/1/Processed").header("Authorization", token)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

    private UserEntity getCustomer() {
        final UserEntity customer = new UserEntity();
        customer.setId(1L);
        customer.setEmail("example@email.com");
        customer.setUserRole(UserRole.CUSTOMER);
        return customer;
    }

    private OrderEntity getOrder() {
        final OrderEntity order = new OrderEntity();
        order.setId(1L);
        order.setCustomer(getCustomer());
        order.setStatus(Status.NotProcessed);
        order.setTotalCost(6);
        return order;
    }
}
