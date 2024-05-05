package com.example.shoop.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Table( name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column( name = "name" , unique = true )
    private String name;

    @Column( name = "description" )
    private String description;

    @Column( name = "ord3r" )
    private Long ord3r;

    @OneToMany( mappedBy="category", cascade=CascadeType.ALL ) // , fetch = FetchType.LAZY
    private List<Product> Products = new ArrayList<>();

    public Category() {}
    public Category( String name ) {
        this.setName( name );
    }

    public String getSize(){
        if ( this.Products!=null ) { return ""+Products.size(); }
        return ""+0;
    }

    @Override
    public String toString() {
        return "Category{" +
                "category_id=" + id +
                ", name='" + name + '\'' +
                ", description=" + description +
                ", ord3r='" + ord3r + '\'' +
                ", Products.size=" + Products.size() +
                '}';
    }

    public void setName(String name) {
        if ( name==null ) return;
            name=name.trim();
            if (name.length()>1) {
                this.name = name;
            }
    }
}
