package com.khanfar.project2.DTO;

import lombok.Data;

@Data
public class ProductDTO {

    private Integer id;
    private String slug;
    private String name;
    private String reference;
    private Double price;
    private Double vat;
    private Boolean stockable;
}
