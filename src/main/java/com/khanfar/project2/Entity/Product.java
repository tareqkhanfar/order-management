package com.khanfar.project2.Entity;


import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "product")
@Data
public class Product {

    @Id
    @Column(name = "id")
    private Integer id ;
    @Column(name = "slug")

    private String slug ;

    @Column(name = "name")

    private String name ;

    @Column(name = "reference")

    private String reference ;

    @Column(name = "price")

    private Double price ;

    @Column(name = "vat")

    private Double vat ;

    @Column(name = "stockable")

    private Boolean stockable ;

    @OneToMany(mappedBy = "product")
    private List<Stock> stockList ;

    @OneToMany(mappedBy = "product_order")
    private List<Product_order> product_orders ;



}
