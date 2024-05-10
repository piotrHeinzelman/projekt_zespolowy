package com.example.shoop.repo;

import com.example.shoop.model.Cart;
import com.example.shoop.model.CartItem;
import com.example.shoop.model.Category;
import com.example.shoop.model.UA;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepo extends CrudRepository<Cart, Long> {

    @Query( value = " SELECT id FROM cart WHERE user_name=:user_name AND status = 0 " , nativeQuery = true )
    public Optional<Long> findOpenIdByUserName(@Param("user_name") String user_name );

    @Query( value = " SELECT COUNT(id) FROM cart WHERE user_name=:user_name AND status = 0 " , nativeQuery = true )
    public Long countCartBelongsToUserName(@Param("user_name") String user_name );

}
