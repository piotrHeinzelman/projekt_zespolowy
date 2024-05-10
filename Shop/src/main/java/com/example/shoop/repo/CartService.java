package com.example.shoop.repo;

import com.example.shoop.model.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService implements CartRepo {

    @Autowired private final CartRepo repo;
    public CartService(CartRepo repo) {
        this.repo = repo;
    }

    @Override
    public Optional<Long> findOpenIdByUserName(String user_name) {
        return repo.findOpenIdByUserName(user_name);
    }

    @Override
    public Long countCartBelongsToUserName(String user_name) {
        return repo.countCartBelongsToUserName(user_name);
    }

    @Override public <S extends Cart> S save(S entity) {return repo.save(entity);}
    @Override public <S extends Cart> Iterable<S> saveAll(Iterable<S> entities) {return repo.saveAll(entities);}
    @Override public Optional<Cart> findById(Long aLong) {return repo.findById(aLong);}
    @Override public boolean existsById(Long aLong) {return repo.existsById(aLong);}
    @Override public Iterable<Cart> findAll() {return repo.findAll();}
    @Override public Iterable<Cart> findAllById(Iterable<Long> longs) {return repo.findAllById(longs);}
    @Override public long count() {return repo.count();}
    @Override public void deleteById(Long aLong) {repo.deleteById(aLong);}
    @Override public void delete(Cart entity) {repo.delete(entity);}
    @Override public void deleteAllById(Iterable<? extends Long> longs) {repo.deleteAllById(longs);}
    @Override public void deleteAll(Iterable<? extends Cart> entities) {repo.deleteAll(entities);}
    @Override public void deleteAll() {repo.deleteAll();}

}
