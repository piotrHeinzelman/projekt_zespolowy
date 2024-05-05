package com.example.shoop.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;

@Entity()
@Getter
@Setter
@ToString
@Table( name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "SKU", unique = true)
    private String SKU;

    @Column(name = "name")
    private String name;

//    @Column(name = "description")
//    private Long description;

    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Price price;

//    private Category category;

//    private Picture picture;

//    private ArrayList<Picture> pictures = new ArrayList<>();



    public Product() {}
    public Product( String SKU, String name ) {
        this.setSKU( SKU );
        this.name = name;
    }

    public void setSKU( String SKU ) {
        this.SKU = SKU.toUpperCase();
    }
}
