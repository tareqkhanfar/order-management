package com.khanfar.project2.Entity;


import com.khanfar.project2.DTO.ProductOrderId;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import jakarta.persistence.*;

@IdClass(ProductOrderId.class)
@Entity
@Table(name = "product_order")
@Data
public class ProductOrder {

    @Id
    @Column(name = "product_id")
    private Integer productId ;

    @Id
    @Column(name = "order_id")
    private Integer orderId ;

    @Column(name = "quantity")
    private Integer quantity ;

    @Column(name = "price")

    private Double price ;

    @Column(name = "vat")

    private Double vat ;


    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product ;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order ;
}
