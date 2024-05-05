package com.example.shoop.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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

    public Category() {}
    public Category( String name ) {
        this.name = name;
    }
}
