package com.example.shoop.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Table( name = "picture")
public class Picture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pict_id")
    private Long pict_id;


    @ManyToOne
    @JoinColumn(name="product_id", nullable=false)
    private Product product;


    @Column( name = "name" , unique = true )
    private String name;

    @Column( name = "ord3r" )
    private String ord3r;

    public Picture() {}

    public Picture( Product product, String name ) {
        this.product = product;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Picture{" +
                "pict_id=" + pict_id +
                ", product=" + product.getId() +
                ", name='" + name + '\'' +
                '}';
    }
}
