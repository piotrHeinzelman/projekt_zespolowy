package com.example.shoop.repo;

import com.example.shoop.model.Price;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PriceService implements PriceRepo {

    @Autowired private final PriceRepo repo;
    public PriceService(PriceRepo repo) { this.repo = repo; }

    @Override
    public <S extends Price> S save(S entity) {
        return repo.save(entity);
    }

    @Override
    public <S extends Price> Iterable<S> saveAll(Iterable<S> entities) {
        return repo.saveAll(entities);
    }

    @Override
    public Optional<Price> findById(Long aLong) {
        return repo.findById(aLong);
    }

    @Override
    public boolean existsById(Long aLong) {
        return repo.existsById(aLong);
    }

    @Override
    public Iterable<Price> findAll() {
        return repo.findAll();
    }

    @Override
    public Iterable<Price> findAllById(Iterable<Long> longs) {
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
    public void delete(Price entity) {
        repo.delete(entity);
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {
        repo.deleteAllById(longs);
    }

    @Override
    public void deleteAll(Iterable<? extends Price> entities) {
        repo.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        repo.deleteAll();
    }
}