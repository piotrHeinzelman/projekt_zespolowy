package com.example.Shop.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Entity
@Table(name = "description")
public class Description {

    @Id
    @Column(name = "id")
    private Long id;

    @Lob
    @Column(name = "description")
    private String description;


}
