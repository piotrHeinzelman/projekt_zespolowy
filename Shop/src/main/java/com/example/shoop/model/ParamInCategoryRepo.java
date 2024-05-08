package com.example.shoop.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParamInCategoryRepo extends CrudRepository<ParamInCategory, KeyCatParam> {
/*
    @Query( value = " (( SELECT parent_id  FROM children_inline WHERE children_id = :childrenId ) UNION ( SELECT parent_id  FROM children_in_table WHERE children_id = :childrenId )) \n " , nativeQuery = true )
    List<String> getParentCountInTableAndInlineByChildrenId (@Param("childrenId") Long childrenId  );

    @Query( value = " SELECT  chi.code_string AS childrenCode , c.children_id , n.indd_name,  n.indd_inline_description,  p.hurt, p.promo, p.price FROM children_inline AS c LEFT JOIN code AS chi ON ( chi.code_id = c.children_id ) LEFT JOIN indd_names AS n ON ( c.children_id = n.code_id AND ( n.lang_id =:langId ) )  LEFT JOIN prices AS p ON ( p.code_id = children_id  AND ( p.lang_id =:langId )  AND ( issue_id = :issueId   ))    WHERE ( c.parent_id = :codeId ) ORDER BY c.item_order ASC " , nativeQuery = true )
    List<Map<String, Object>> findAnyRowByCodeId(@Param("codeId") Long codeId , @Param("langId") Long langId, @Param("issueId") Long issueId );

    @Query( value = "SELECT parent_id FROM children_inline WHERE  children_id = :childrenId " , nativeQuery = true )
    List<Map<String, Object>> isChild ( @Param("childrenId") Long childrenId );

    @Query( value = "SELECT TOP (1) c.parent_id FROM children_inline AS c WHERE  c.parent_id = :parentId " , nativeQuery = true )
    Long whenIsParentGetParentId ( @Param("parentId") Long parentId );

    @Query( value = "SELECT TOP (1) c.parent_id FROM children_inline AS c WHERE  c.children_id = :childrenId " , nativeQuery = true )
    Long getParent ( @Param("childrenId") Long childrenId );


    @Modifying
    @Transactional
    @Query( value = "DELETE FROM children_inline WHERE children_id=:codeId OR parent_id=:codeId " , nativeQuery = true  )
    int clearCodeId (@Param("codeId") Long codeId );

    @Query( value = " SELECT MAX(item_order) FROM children_inline WHERE parent_id=:codeId " , nativeQuery = true  )
    Long maxOrdByCodeId (@Param("codeId") Long codeId );



    @Query( value = " SELECT  I.children_id " +
            " FROM children_inline AS I " +
            " WHERE ( I.parent_id = :codeId ) " , nativeQuery = true )
    List< Long > findChildrensCodeByParentCodeId( @Param("codeId") Long codeId );


    @Query( value = "  SELECT * FROM docstrans.children_inline WHERE parent_id=:codeId ORDER BY item_order ASC " , nativeQuery = true )
    List<ChildrenInTable> getSortByParentCodeId( @Param("codeId") Long codeId );
*/
}
