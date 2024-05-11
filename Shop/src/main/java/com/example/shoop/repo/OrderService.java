package com.example.shoop.repo;

import com.example.shoop.model.Ord3r;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService implements OrderRepo {

    @Autowired
    private final OrderRepo repo;

    public OrderService(OrderRepo repo) {
        this.repo = repo;
    }

    @Override public <S extends Ord3r> S save(S entity) {return repo.save(entity);}
    @Override public <S extends Ord3r> Iterable<S> saveAll(Iterable<S> entities) {return repo.saveAll(entities);}
    @Override public Optional<Ord3r> findById(Long aLong) {return repo.findById(aLong);}
    @Override public boolean existsById(Long aLong) {return repo.existsById(aLong);}
    @Override public Iterable<Ord3r> findAll() {return repo.findAll();}
    @Override public Iterable<Ord3r> findAllById(Iterable<Long> longs) {return repo.findAllById(longs);}
    @Override public long count() {return repo.count();}
    @Override public void deleteById(Long aLong) {repo.deleteById(aLong);}
    @Override public void delete(Ord3r entity) {repo.delete(entity);}
    @Override public void deleteAllById(Iterable<? extends Long> longs) {repo.deleteAllById(longs);}
    @Override public void deleteAll(Iterable<? extends Ord3r> entities) {repo.deleteAll(entities);}
    @Override public void deleteAll() {repo.deleteAll();}
}
