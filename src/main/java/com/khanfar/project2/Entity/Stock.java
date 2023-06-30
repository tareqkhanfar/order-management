package com.khanfar.project2.Entity;


import lombok.Data;

import javax.persistence.*;
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

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;


    @JoinColumn(name = "id")
    @ManyToOne
    private Product product ;

}
