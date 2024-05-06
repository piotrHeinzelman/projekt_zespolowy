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

    @Column(name = "status")
    private Status status;


    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Price price;




    @OneToMany(mappedBy="product")
    private Set<Picture> pictures = new HashSet<>();



    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="category_id", referencedColumnName = "id")
    private Category category;




// CART ONE to MANY
//    https://www.baeldung.com/hibernate-one-to-many

    public Product() {}
    public Product( String SKU, String name ) {
        this.setSKU( SKU );
        this.name = name;
        this.status=Status.ACTIVE;
    }



    public void setSKU(String SKU ) {
        this.SKU = SKU.toUpperCase();
    }


    public String getCategoryName() {
        if (category!=null){
            return category.getName();
        }
        return "  ";
    }

    public Long getCategoryId() {
        if (category!=null){
            return category.getId();
        }
        return null;
    }

    public Long getStatus() {
        return Status.getAsLong( status );
    }
}
