package com.example.shoop.repo;

import com.example.shoop.model.Parm;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface ParamRepo extends CrudRepository<Parm, Long> {

    //SELECT V.* , P.* FROM PARAM_VAL AS V JOIN PARM AS P ON (  V.PARAM_ID=P.PARAM_ID ) WHERE PRODUCT_ID=:product_id

      @Query( value = " SELECT  P.NAME AS name, V.VALE AS val, P.UNT AS unit FROM PARAM_VAL AS V  JOIN PARM AS P ON (  V.PARAM_ID=P.PARAM_ID ) WHERE PRODUCT_ID=:product_id  ORDER BY P.ITEM_ORDER; " , nativeQuery = true )
       public Iterable<Map<String, String>> findAllByProductId(@Param("product_id") Long product_id   );

}
