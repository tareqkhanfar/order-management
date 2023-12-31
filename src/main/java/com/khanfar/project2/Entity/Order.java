package com.khanfar.project2.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Order_")
@Data
public class Order {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "ordered_At")
    private LocalDateTime orderedAt;

    @OneToMany(mappedBy = "order")
    private List<ProductOrder> productOrders;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
