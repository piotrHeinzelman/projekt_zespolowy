package com.example.shoop.repo;

import com.example.shoop.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements ProductRepo {

    @Autowired private final ProductRepo repo;
    public ProductService(ProductRepo repo) { this.repo = repo; }

    public <S extends Product> S save(S entity) {
        return repo.save(entity);
    }


    @Override
    public List<Product> getAllProductsFromCartByCartId(Long cartId) {
        return repo.getAllProductsFromCartByCartId(cartId);
    }

    @Override
    public List<Product> findByFragName(String find_name) {
        return repo.findByFragName(find_name);
    }

    @Override public <S extends Product> Iterable<S> saveAll(Iterable<S> entities) {
        return repo.saveAll(entities);
    }
    @Override public Optional<Product> findById(Long aLong) {
        return repo.findById(aLong);
    }
    @Override public boolean existsById(Long aLong) {
        return repo.existsById(aLong);
    }
    @Override public Iterable<Product> findAll() {
        return repo.findAll();
    }
    @Override public Iterable<Product> findAllById(Iterable<Long> longs) {
        return repo.findAllById(longs);
    }
    @Override public long count() {
        return repo.count();
    }
    @Override public void deleteById(Long aLong) {
        repo.deleteById(aLong);
    }
    @Override public void delete(Product entity) {
        repo.delete(entity);
    }
    @Override public void deleteAllById(Iterable<? extends Long> longs) {
        repo.deleteAllById(longs);
    }
    @Override public void deleteAll(Iterable<? extends Product> entities) {
        repo.deleteAll(entities);
    }
    @Override public void deleteAll() {
        repo.deleteAll();
    }

}
