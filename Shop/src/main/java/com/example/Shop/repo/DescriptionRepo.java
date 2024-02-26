package com.example.Shop.repo;

import com.example.Shop.model.Description;
import com.example.Shop.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DescriptionRepo extends CrudRepository<Description, Long> {
}
