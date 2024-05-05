package com.example.shoop.repo;

import com.example.shoop.model.Picture;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PictureRepo extends CrudRepository<Picture, Long> {
}
