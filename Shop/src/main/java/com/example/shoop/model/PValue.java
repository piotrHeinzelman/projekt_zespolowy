package com.example.shoop.model;


import com.example.shoop.repo.KeyProdParam;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity

@Getter
@Setter
@Table( name = "paramVal")

@IdClass( KeyProdParam.class )
public class PValue {

    @Id
    @Column(name = "product_id")
    private Long product_id;

    @Id
    @Column(name = "param_id")
    private Long param_id;

    @Column(name = "vale")
    private String vale;

    public PValue() {}
    public PValue(Long product_id, Long param_id, String vale) {
        this.product_id = product_id;
        this.param_id = param_id;
        this.vale = vale;
    }
    public PValue(Product product, Parm param, String vale) {
        this.product_id = product.getId();
        this.param_id = param.getParam_id();
        this.vale = vale;
    }


    /*
    public ChildrenInline( KeyParentChildren keyParentChildren ) {
        if ( keyParentChildren.getParentId()==keyParentChildren.getChildrenId() ){ throw new RuntimeException("ParentCode=ChildCode"); }
        this.parentId = keyParentChildren.getParentId();
        this.childrenId = keyParentChildren.getChildrenId();
    }

    // comparator
    public static int compare ( ChildrenInline one , ChildrenInline two  ) {
        return Long.compare(   one.getItemOrder() , two.getItemOrder() ) ;
    }



    @Override
    public String toString() {
        return "ChildrenInline{" +
                "parentId=" + parentId +
                ", childrenId=" + childrenId +
                ", itemOrder=" + itemOrder +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChildrenInline that = (ChildrenInline) o;
        return parentId.equals(that.parentId) && childrenId.equals(that.childrenId) && Objects.equals(itemOrder, that.itemOrder);
    }

    @Override
    public int hashCode() {
        return Objects.hash(parentId, childrenId);
    }
*/
}
