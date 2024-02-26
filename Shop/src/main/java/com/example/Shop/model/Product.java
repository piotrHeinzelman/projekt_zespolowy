package com.example.Shop.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column(name = "id")
    private Long id;

    @Column(name = "SKU", unique = true)
    private String SKU;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name="status")
    private Long status;


}
