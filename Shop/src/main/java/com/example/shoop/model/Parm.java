package com.example.shoop.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Table( name = "parm")
public class Parm {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "param_id")
    private Long param_id;


    @Column( name = "name" )
    private String name;

    @Column(name = "unt")
    private String unt;

    @Column(name = "item_order")
    private Long item_order;

    public Parm() {}

    public Parm(String name) { this.name = name; }

    public Parm(String name, String unit) {
        this.name = name;
        this.unt = unit;
    }
}
