package com.example.Shop.repo;

import com.example.Shop.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepo extends CrudRepository<Product, Long> {

    public Optional<Product> getBySKU(String sku);


}
