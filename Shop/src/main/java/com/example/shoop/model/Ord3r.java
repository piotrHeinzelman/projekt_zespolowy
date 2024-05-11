package com.example.shoop.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@Table(name = "ord3r")
public class Ord3r {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    @Column(name = "status")
    private OrderStatus status;

    @OneToMany(mappedBy = "ord3r")
    private Set<OrderItem> orderItems = new HashSet<>();

    @Column(name = "req_timestamp")
    private Timestamp requestTime_stamp;

    @Column(name = "sum")
    private Double sum;

 // address

    @Column(name = "email")
    private String email;

    @Column(name = "first_Name")
    private String first_Name;

    @Column(name = "last_Name")
    private String last_Name;

    @Column(name = "postal_Code")
    private String postal_Code;

    @Column(name = "Stre3t")
    private String Stre3t;

    @Column(name = "numb")
    private String numb;

    @Column(name = "theCity")
    private String theCity;

    @Column(name = "mobil")
    private String mobil;


    public Ord3r() {
    }

    public Ord3r(String email) {
        this.email = email;
        this.status = OrderStatus.InProgress;
        this.requestTime_stamp = Timestamp.from(Instant.now());
    }


}