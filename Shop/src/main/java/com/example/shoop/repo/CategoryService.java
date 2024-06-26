package com.example.shoop.repo;

import com.example.shoop.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService implements CategoryRepo {

    @Autowired private final CategoryRepo repo;

    public CategoryService(CategoryRepo repo) { this.repo = repo; }

    @Override
    public <S extends Category> S save(S entity) {
        return repo.save(entity);
    }

    @Override
    public <S extends Category> Iterable<S> saveAll(Iterable<S> entities) {
        return repo.saveAll(entities);
    }

    @Override
    public Optional<Category> findById(Long aLong) {
        return repo.findById(aLong);
    }

    @Override
    public boolean existsById(Long aLong) {
        return repo.existsById(aLong);
    }

    @Override
    public Iterable<Category> findAll() {
        return repo.findAll();
    }

    @Override
    public Iterable<Category> findAllById(Iterable<Long> longs) {
        return repo.findAllById(longs);
    }

    @Override
    public long count() {
        return repo.count();
    }

    @Override
    public void deleteById(Long aLong) {
        repo.deleteById(aLong);
    }

    @Override
    public void delete(Category entity) {
        repo.delete(entity);
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {
        repo.deleteAllById(longs);
    }

    @Override
    public void deleteAll(Iterable<? extends Category> entities) {
        repo.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        repo.deleteAll();
    }
}
