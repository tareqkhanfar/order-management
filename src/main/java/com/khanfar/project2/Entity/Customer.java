package com.khanfar.project2.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Customer")
@Data
public class Customer {

    @Id
    @Column(name = "id")
    private  Integer id ;
    @Column(name = "first_name")

    private String firstName ;
    @Column(name = "last_name")

    private String lastName ;
    @Column(name = "born_at")

    private Date bornAt ;


    @JsonManagedReference

    @OneToMany(mappedBy = "customer")
    private List<Order> orderList ;
}
