package com.example.Shop.service;

import com.example.Shop.model.Description;
import com.example.Shop.model.Product;
import com.example.Shop.repo.DescriptionRepo;
import com.example.Shop.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class DesriptionService implements DescriptionRepo {

    @Autowired private DescriptionRepo repo;


    // ********* delegate *************
    @Override public <S extends Description> S save(S entity) {return repo.save(entity);}
    @Override public <S extends Description> Iterable<S> saveAll(Iterable<S> entities) {return repo.saveAll(entities);}
    @Override public Optional<Description> findById(Long aLong) {return repo.findById(aLong);}
    @Override public boolean existsById(Long aLong) {return repo.existsById(aLong);}
    @Override public Iterable<Description> findAll() {return repo.findAll();}
    @Override public Iterable<Description> findAllById(Iterable<Long> longs) {return repo.findAllById(longs);}
    @Override public long count() {return repo.count();}
    @Override public void deleteById(Long aLong) {repo.deleteById(aLong);}
    @Override public void delete(Description entity) {repo.delete(entity);}
    @Override public void deleteAllById(Iterable<? extends Long> longs) {repo.deleteAllById(longs);}
    @Override public void deleteAll(Iterable<? extends Description> entities) {repo.deleteAll(entities);}
    @Override public void deleteAll() {repo.deleteAll();}
}
