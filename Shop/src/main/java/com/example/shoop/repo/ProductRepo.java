package com.example.shoop.repo;

import com.example.shoop.model.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends CrudRepository<Product, Long> {

    @Query( value = " SELECT * FROM PRODUCT AS P JOIN CART_ITEM AS CI  ON (  CI.CART_ID=:cartId AND  CI.ID=P.ID ) " , nativeQuery = true )
    public List<Product> getAllProductsFromCartByCartId(@Param("cartId") Long cartId   );

}
