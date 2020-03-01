package com.example.demo.entity;

import com.example.demo.security.UserRole;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

import lombok.Data;

@Data
@Entity(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String fio;
    private LocalDate birthDate;
    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<OrderEntity> order_id = new ArrayList<>();
    private UserRole userRole;
}
