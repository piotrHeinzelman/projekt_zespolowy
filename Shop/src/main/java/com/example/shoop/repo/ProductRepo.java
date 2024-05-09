package com.example.shoop.repo;

import com.example.shoop.model.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends CrudRepository<Product, Long> {

  //  @Query( value = " SELECT * FROM PRODUCT WHERE CATEGORY_ID=:categoryId " , nativeQuery = true )
  //   public Iterable<Product> findAllOfCategory( @Param("categoryId") Long categoryId   );

}
