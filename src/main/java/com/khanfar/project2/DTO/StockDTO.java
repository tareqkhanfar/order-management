package com.khanfar.project2.DTO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StockDTO {

    private Integer id;
    private Integer quantity;
    private LocalDateTime updatedAt;
    private Integer productId;

    // Constructors, getters, and setters
}
