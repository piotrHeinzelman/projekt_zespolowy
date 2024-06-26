package com.example.shoop.repo;

import com.example.shoop.model.CartItem;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemRepo extends CrudRepository<CartItem, Long> {

    //@Query( value = " SELECT id FROM cart WHERE user_name=:user_name AND status = 0 " , nativeQuery = true )
    //public Optional<Long> findOpenIdByUserName(@Param("user_name") String user_name );

    @Query( value = " SELECT * FROM CART_ITEM WHERE CART_ID=:cartId " , nativeQuery = true )
    public List<CartItem> getAllItemByCartId(@Param("cartId") Long cartId   );

    @Query( value = " SELECT * FROM CART_ITEM WHERE CART_ID=:cartId AND PRODUCT_ID=:productId" , nativeQuery = true )
    public Optional<CartItem> getByCartIdProdId( @Param("cartId") Long cartId , @Param("productId") Long productId );


    @Transactional
    @Modifying
    @Query( value = " DELETE FROM CART_ITEM WHERE CART_ID=:cartId " , nativeQuery = true )
    public void clearCart( @Param("cartId") Long cartId );

}
