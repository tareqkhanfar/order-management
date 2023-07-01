package com.khanfar.project2.DTO;

import lombok.Data;

@Data
public class ProductOrderDTO {

    private Integer productId;
    private Integer orderId;
    private Integer quantity;
    private Double price;
    private Double vat;

    // Constructors, getters, and setters
}
