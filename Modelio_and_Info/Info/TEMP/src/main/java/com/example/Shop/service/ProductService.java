package com.example.Shop.service;

import com.example.Shop.model.Product;
import com.example.Shop.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService implements ProductRepo {

    @Autowired private ProductRepo repo;

    @Override
    public Optional<Product> getBySKU(String sku) {
        return repo.getBySKU(sku);
    }

    // ********* delegate *************
    @Override public <S extends Product> S save(S entity) {return repo.save(entity);}
    @Override public <S extends Product> Iterable<S> saveAll(Iterable<S> entities) {return repo.saveAll(entities);}
    @Override public Optional<Product> findById(Long aLong) {return repo.findById(aLong);}
    @Override public boolean existsById(Long aLong) {return repo.existsById(aLong);}
    @Override public Iterable<Product> findAll() {return repo.findAll();}
    @Override public Iterable<Product> findAllById(Iterable<Long> longs) {return repo.findAllById(longs);}
    @Override public long count() {return repo.count();}
    @Override public void deleteById(Long aLong) {repo.deleteById(aLong);}
    @Override public void delete(Product entity) {repo.delete(entity);}
    @Override public void deleteAllById(Iterable<? extends Long> longs) {repo.deleteAllById(longs);}
    @Override public void deleteAll(Iterable<? extends Product> entities) {repo.deleteAll(entities);}
    @Override  public void deleteAll() { repo.deleteAll(); }

}
