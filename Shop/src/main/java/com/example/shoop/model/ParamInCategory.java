package com.example.shoop.model;


import com.example.shoop.repo.KeyCatParam;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity

@Getter
@Setter
@Table( name = "param_in_category")

@IdClass( KeyCatParam.class )
public class ParamInCategory {

    @Id
    @Column(name = "category_id")
    private Long category_id;

    @Id
    @Column(name = "parameter_id")
    private Long parameter_id;


    public ParamInCategory() {}

    public ParamInCategory( Long category_id, Long parameter_id ) {
        this.category_id = category_id;
        this.parameter_id = parameter_id;
    }

    public ParamInCategory( Category category, Parm parameter ) {
        this.category_id = category.getId();
        this.parameter_id = parameter.getParam_id();
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

