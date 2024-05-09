package com.example.shoop.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.time.Instant;


import java.sql.Timestamp;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Table( name = "cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_name")
    private String user_name;

    @Column(name = "status" )
    private CartStatus status;

    @Transient
    private List<CartItem> items;


    @Column(name = "req_timestamp" )
    private Timestamp requestTime_stamp;


    @Column(name = "first_Name" )
    private String first_Name;

    @Column(name = "last_Name" )
    private String last_Name;

    @Column(name = "postal_Code" )
    private String postal_Code;

    @Column(name = "Stre3t" )
    private String Stre3t;

    @Column(name = "numb" )
    private String numb;

    @Column(name = "theCity" )
    private String theCity;

    @Column(name = "mobil" )
    private String mobil;

    @Column(name="sum")
    private Double sum;

    public Cart() {}
    public Cart(String user_name ) {
        this.user_name = user_name;
        this.status = CartStatus.InProgress;
        this.requestTime_stamp = Timestamp.from(Instant.now());
    }
}
