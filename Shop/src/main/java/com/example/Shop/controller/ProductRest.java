package com.example.Shop.controller;
/*

// https://www.baeldung.com/spring-data-rest-intro


@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface UserRepository extends PagingAndSortingRepository<WebsiteUser, Long> {
    List<WebsiteUser> findByName(@Param("name") String name);


    https://spring.io/guides/tutorials/react-and-spring-data-rest
}

http://127.0.0.1:8088/products/search/findByName?name=Karta%20graficzna%20NVidia%20RTX%203080
 */

import com.example.Shop.model.Product;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "products", path = "products")
public interface ProductRest extends PagingAndSortingRepository<Product, Long> {
    List<Product> findByName( @Param("name") String name );
    Optional<Product> findById(@Param("id") Long id );

}
