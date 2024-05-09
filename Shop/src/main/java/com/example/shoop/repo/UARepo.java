package com.example.shoop.repo;

import com.example.shoop.model.UA;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UARepo extends CrudRepository<UA, Long> {

    @Query( value = " SELECT * FROM ADRS  WHERE UA_ID =:email " , nativeQuery = true )
    public Optional<UA> findByUA_ID( @Param("email") String email );

}
