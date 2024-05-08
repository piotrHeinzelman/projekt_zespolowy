package com.example.shoop.repo;

import com.example.shoop.model.Param;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParamRepo extends CrudRepository<Param, Long> {
}
