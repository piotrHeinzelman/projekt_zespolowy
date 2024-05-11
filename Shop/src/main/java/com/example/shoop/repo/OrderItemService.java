package com.example.shoop.repo;

import com.example.shoop.model.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderItemService implements OrderItemRepo {

    @Autowired
    private final OrderItemRepo repo;

    public OrderItemService(OrderItemRepo repo) {
        this.repo = repo;
    }

    @Override public <S extends OrderItem> S save(S entity) {return repo.save(entity);}
    @Override public <S extends OrderItem> Iterable<S> saveAll(Iterable<S> entities) {return repo.saveAll(entities);}
    @Override public Optional<OrderItem> findById(Long aLong) {return repo.findById(aLong);}
    @Override public boolean existsById(Long aLong) {return repo.existsById(aLong);}
    @Override public Iterable<OrderItem> findAll() {return repo.findAll();}
    @Override public Iterable<OrderItem> findAllById(Iterable<Long> longs) {return repo.findAllById(longs);}
    @Override public long count() {return repo.count();}
    @Override public void deleteById(Long aLong) {repo.deleteById(aLong);}
    @Override public void delete(OrderItem entity) {repo.delete(entity);}
    @Override public void deleteAllById(Iterable<? extends Long> longs) {repo.deleteAllById(longs);}
    @Override public void deleteAll(Iterable<? extends OrderItem> entities) {repo.deleteAll(entities);}
    @Override public void deleteAll() {repo.deleteAll();}
}
