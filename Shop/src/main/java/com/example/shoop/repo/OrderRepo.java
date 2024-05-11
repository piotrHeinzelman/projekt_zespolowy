package com.example.shoop.repo;

import com.example.shoop.model.Ord3r;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepo extends CrudRepository<Ord3r, Long> {
}

