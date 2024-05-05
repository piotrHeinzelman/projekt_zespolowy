package com.example.shoop.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Table( name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long category_id;

    @Column( name = "name" , unique = true )
    private String name;

    @Column( name = "description" )
    private Long description;

    @OneToMany(targetEntity=Product.class, mappedBy="category",cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Product> Products = new ArrayList<>();

    public Category() {}
    public Category( String name ) {
        this.name = name;
    }
}
