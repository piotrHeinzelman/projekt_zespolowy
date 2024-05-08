package com.example.shoop.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity

@Getter
@Setter
@Table( name = "children_inline")

@IdClass( LongLong.class )
public class ChildrenInline {

    @Id
    @Column(name = "icon_id")
    private Long icon_id;

    @Id
    @Column(name = "icon_group_id")
    private Long icon_group_id;

    @Column(name = "item_order")
    private Long itemOrder;

    public ChildrenInline() {}
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

