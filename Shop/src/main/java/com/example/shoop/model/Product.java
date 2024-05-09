package com.example.shoop.model;

import com.example.shoop.crewControllers.ProductController;
import com.example.shoop.repo.ProductService;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity()
@Getter
@Setter
@ToString
@Table( name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "SKU", unique = true)
    private String SKU;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private Status status;


    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Price price;




    @OneToMany(mappedBy="product")
    private Set<Picture> pictures = new HashSet<>();



    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="category_id", referencedColumnName = "id")
    private Category category;




// CART ONE to MANY
//    https://www.baeldung.com/hibernate-one-to-many

    public Product() {}
    public Product( String SKU, String name ) {
        this.setSKU( SKU );
        this.name = name;
        this.status=Status.ACTIVE;
    }



    public void setSKU(String SKU ) {
        this.SKU = SKU.toUpperCase();
    }


    public String getCategoryName() {
        if (category!=null){
            return category.getName();
        }
        return "  ";
    }

    public Long getCategoryId() {
        if (category!=null){
            return category.getId();
        }
        return null;
    }

    public Long getStatus() {
        return Status.getAsLong( status );
    }


    public Double getPriceVal(){
        if (price==null) return null;
        return price.getPrice();
    }

    public Double getPromoVal(){
        if (price==null || price.getPromo()==null ) {return null;}
        return price.getPromo();
    }

    public Double getValue() {
        if (price==null) return null;
        return price.getValue();
    }

    public String getValueAsString00() {
        if (price==null) return "";
         return String.format("%,.2f", price.getValue() );
    }

    public String getPictAddr(){
        if ( pictures!=null && pictures.size()>0) { return ""+pictures.iterator().next().getPict_id(); }
        return "blank";
    }

}
