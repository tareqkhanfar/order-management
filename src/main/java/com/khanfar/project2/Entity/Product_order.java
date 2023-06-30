package com.khanfar.project2.Entity;


import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "product_order")
@Data
public class Product_order {

    @Id
    @Column(name = "product_id")
    private Integer product_id ;

    @Id
    @Column(name = "order_id")
    private Integer order_id ;

    @Column(name = "quantity")
    private Integer quantity ;

    @Column(name = "price")

    private Double price ;

    @Column(name = "vat")

    private Double vat ;


    @ManyToOne
    @JoinColumn(name = "id")
    private Product product ;

    @ManyToOne
    @JoinColumn(name = "id")
    private Order order ;
}
