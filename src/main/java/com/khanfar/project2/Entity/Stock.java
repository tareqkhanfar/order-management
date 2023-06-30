package com.khanfar.project2.Entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "stock")
@Data
public class Stock {


    @Id
    @Column(name = "id")
    private Integer id ;

    @Column(name = "quantity")
    private Integer quantity ;

    @Column(name = "update_at")
    private LocalDateTime updatedAt;


    @JoinColumn(name = "product_id")
    @ManyToOne
    private Product product ;

}
