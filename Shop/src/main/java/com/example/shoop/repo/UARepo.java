package com.example.shoop.repo;

import com.example.shoop.model.UA;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UARepo extends CrudRepository<UA, Long> {

    @Query( value = " SELECT * FROM ADRS WHERE EMAIL=:email_add " , nativeQuery = true )
    public Iterable<UA> findByEmail(@Param("email_add") String email_add );

}
