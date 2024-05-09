package com.example.shoop.repo;

import com.example.shoop.model.Cart;
import com.example.shoop.model.CartItem;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartItemRepo extends CrudRepository<CartItem, Long> {

    //@Query( value = " SELECT id FROM cart WHERE user_name=:user_name AND status = 0 " , nativeQuery = true )
    //public Optional<Long> findOpenIdByUserName(@Param("user_name") String user_name );

}
