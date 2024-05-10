package com.example.shoop.repo;

import com.example.shoop.model.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends CrudRepository<Product, Long> {

    //@Query( value = " SELECT CI.ID, P.SKU, P.NAME, P.STATUS,CI.CART_ID, CI.PRICE_PER_ITEM, CI.PRODUCT_ID, CI.QUANTITY FROM PRODUCT AS P JOIN CART_ITEM AS CI  ON (  CI.PRODUCT_ID=P.ID ) WHERE CI.CART_ID=:cartId " , nativeQuery = true )
    @Query( value = " SELECT P.* FROM PRODUCT AS P JOIN CART_ITEM AS CI  ON (  CI.PRODUCT_ID=P.ID ) WHERE CI.CART_ID=:cartId " , nativeQuery = true )
    public List<Product> getAllProductsFromCartByCartId(@Param("cartId") Long cartId   );

    //SELECT * FROM PRODUCT WHERE NAME LIKE ('%a%')
    @Query( value = " SELECT * FROM PRODUCT WHERE ( NAME LIKE (%:find_name%)  OR SKU LIKE (%:find_name%) ) " , nativeQuery = true )
    public List<Product> findByFragName( String find_name );

}
