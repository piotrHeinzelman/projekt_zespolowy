package com.example.Shop.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

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

    public Product() {}
    public Product(String SKU, String name) {
        this.SKU = SKU.toUpperCase();
        this.name = name;
        this.status = 1L;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) && Objects.equals(SKU, product.SKU);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, SKU);
    }

    public void fullUpdate( Product other ){
        this.SKU=other.getSKU();
        this.name=other.name;
        this.status=other.status;
    }

}
