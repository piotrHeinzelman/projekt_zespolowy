package com.example.shoop.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Table( name = "orderItem")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "orderItem_id")
    private Long orderItem_id;

    @Column(name = "product_id")
    private Long product_id;

    @Column(name = "quantity")
    private Long quantity;

    @Column(name = "pricePerItem")
    private Double pricePerItem;

    @Column(name = "product_SKU")
    private String product_SKU;

    @Column(name = "priceSum")
    private String priceSum;

    public OrderItem() {}

}
