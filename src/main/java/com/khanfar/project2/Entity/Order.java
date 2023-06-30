package com.khanfar.project2.Entity;


import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Order")
@Data
public class Order {


    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "ordered_At")
    private LocalDateTime orderedAt ;

    @OneToMany(mappedBy = "order")
    private List<Product_order> product_orders ;


    @ManyToOne
    @JoinColumn(name = "id")
    private Customer customer ;
}
