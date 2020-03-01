package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "order_item")
public class OrderItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer amount;
    @ManyToOne(optional = false)
    @JoinColumn(name = "beer_id", nullable = false)
    private BeerEntity beer;
    @ManyToOne(optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    private OrderEntity orderEntity;
}
