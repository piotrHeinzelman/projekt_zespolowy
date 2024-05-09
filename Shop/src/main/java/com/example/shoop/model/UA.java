package com.example.shoop.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Table( name = "ADRS")
public class UA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ua_id")
    private Long ua_id;

    @Column(name = "email" , unique = true)
    private Long email;

    @Column(name = "first_Name" )
    private String first_Name;

    @Column(name = "last_Name" )
    private String last_Name;

    @Column(name = "postal_Code" )
    private String postal_Code;

    @Column(name = "Stre3t" )
    private String Stre3t;

    @Column(name = "numb" )
    private String numb;

    @Column(name = "theCity" )
    private String theCity;

    @Column(name = "mobil" )
    private String mobil;

    public UA() {}
    public UA(Long email) { this.email = email; }

}
