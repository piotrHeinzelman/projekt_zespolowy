package com.example.shoop.repo;

import com.example.shoop.model.CartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartItemService implements CartItemRepo {

    @Autowired
    private final CartItemRepo repo;
    public CartItemService( CartItemRepo repo) { this.repo = repo; }

    @Override
    public List<CartItem> getAllItemByCartId(Long cartId) {
        return repo.getAllItemByCartId(cartId);
    }

    @Override public <S extends CartItem> S save(S entity) {return repo.save(entity);}
    @Override public <S extends CartItem> Iterable<S> saveAll(Iterable<S> entities) {return repo.saveAll(entities);}
    @Override public Optional<CartItem> findById(Long aLong) {return repo.findById(aLong);}
    @Override public boolean existsById(Long aLong) {return repo.existsById(aLong);}
    @Override public Iterable<CartItem> findAll() {return repo.findAll();}
    @Override public Iterable<CartItem> findAllById(Iterable<Long> longs) {return repo.findAllById(longs);}
    @Override public long count() {return repo.count();}
    @Override public void deleteById(Long aLong) {repo.deleteById(aLong);}
    @Override public void delete(CartItem entity) {repo.delete(entity);}
    @Override public void deleteAllById(Iterable<? extends Long> longs) {repo.deleteAllById(longs);}
    @Override public void deleteAll(Iterable<? extends CartItem> entities) {repo.deleteAll(entities);}
    @Override public void deleteAll() {repo.deleteAll();}
}
