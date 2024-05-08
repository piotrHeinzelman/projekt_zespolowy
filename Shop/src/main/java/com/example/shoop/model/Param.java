package com.example.shoop.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Table( name = "param")
public class Param {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "param_id")
    private Long param_id;


    @Column( name = "name" )
    private String name;

    @Column(name = "unit")
    private String unit="";

    @Column(name = "item_order")
    private Long itemOrder;

    public Param() {}

    public Param(String name) { this.name = name; }

    public Param(String name, String unit) {
        this.name = name;
        this.unit = unit;
    }
}
