package com.example.demo.entity;

import com.example.demo.dto.Status;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity(name = "order_table")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity customer;
    private Integer totalCost;
    private Status status;
    @OneToMany(mappedBy = "orderEntity", fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<OrderItemEntity> orderItem = new ArrayList<>();
}
