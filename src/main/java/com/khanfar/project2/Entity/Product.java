package com.khanfar.project2.Entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.*;

import lombok.Data;

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

    @OneToMany(mappedBy = "product")
    private List<ProductOrder> product_orders ;



}
