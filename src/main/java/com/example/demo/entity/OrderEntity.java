package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "order")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long customer_id;
    private Double totalCost;
    @ManyToOne(optional = false)
    @JoinColumn(name = "beer_id", nullable = false)
    private BeerEntity beer;
}
