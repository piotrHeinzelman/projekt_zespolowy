package com.example.shoop.repo;

import com.example.shoop.model.Price;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRepo extends CrudRepository<Price, Long> {
}
