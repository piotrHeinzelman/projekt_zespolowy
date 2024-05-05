package com.example.shoop.model;

import com.example.shoop.config.FileTool;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

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

    @Column(name = "description")
    private Long description;



    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Price price;




    @OneToMany(mappedBy="product")
    private Set<Picture> pictures = new HashSet<>();


    //    private Category category;




// CART ONE to MANY
//    https://www.baeldung.com/hibernate-one-to-many

    public Product() {}
    public Product( String SKU, String name ) {
        this.setSKU( SKU );
        this.name = name;
    }



    public void setSKU(String SKU ) {
        this.SKU = SKU.toUpperCase();
    }
}
