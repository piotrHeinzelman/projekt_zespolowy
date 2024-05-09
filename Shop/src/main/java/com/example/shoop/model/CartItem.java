package com.example.shoop.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Table( name = "cart_item")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "product_id")
    private Long product_id;

    @Column(name = "cart_id")
    private Long cart_id;

    @Column(name = "quantity")
    private Long quantity;

    @Column(name = "pricePerItem")
    private Double pricePerItem;

    @Transient
    private String product_name;

    @Transient
    private String product_SKU;


    public CartItem() {}
    public CartItem(Long product_id, Long cart_id, Double pricePerItem ) {
        this.product_id = product_id;
        this.cart_id = cart_id;
        this.pricePerItem = pricePerItem;
        this.quantity=1L;
    }
}
