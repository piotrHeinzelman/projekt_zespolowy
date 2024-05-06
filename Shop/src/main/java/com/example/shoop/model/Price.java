package com.example.shoop.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Table( name = "price")
public class Price {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "price" )
    private Double price;

    @Column(name = "promo")
    private Double promo=null;


    public Price() {}
    public Price(Product product, Double price) {
        this.id = product.getId();
        this.product = product;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Price{" +
                "id=" + id +
                ", product=" + product.getId() +
                ", price=" + price +
                '}';
    }

    public Double getValue() {
        if ( promo!=null && promo < price ) { return promo; }
        return price;
    }
}
