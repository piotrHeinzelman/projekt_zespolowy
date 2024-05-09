package com.example.shoop.repo;

import com.example.shoop.model.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends CrudRepository<Product, Long> {

    @Query( value = " SELECT * FROM product WHERE id=:cartId " , nativeQuery = true )
    public List<Product> getAllItemFromCartByCartId(@Param("cartId") Long cartId   );



}
