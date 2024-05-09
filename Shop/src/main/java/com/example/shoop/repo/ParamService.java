package com.example.shoop.repo;

import com.example.shoop.model.Parm;
import com.example.shoop.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class ParamService implements ParamRepo {

    @Autowired private final ParamRepo repo;

    public ParamService(ParamRepo repo) { this.repo = repo; }


    @Override
    public Iterable<Map<String, String>> findAllByProductId(Long product_id) {
        return repo.findAllByProductId(product_id);
    }

    @Override public <S extends Parm> S save(S entity) {
        return repo.save(entity);
    }
    @Override public <S extends Parm> Iterable<S> saveAll(Iterable<S> entities) {
        return repo.saveAll(entities);
    }
    @Override public Optional<Parm> findById(Long aLong) {
        return repo.findById(aLong);
    }
    @Override public boolean existsById(Long aLong) {
        return repo.existsById(aLong);
    }
    @Override public Iterable<Parm> findAll() {
        return repo.findAll();
    }
    @Override public Iterable<Parm> findAllById(Iterable<Long> longs) {
        return repo.findAllById(longs);
    }
    @Override public long count() {
        return repo.count();
    }
    @Override public void deleteById(Long aLong) {
        repo.deleteById(aLong);
    }
    @Override public void delete(Parm entity) {
        repo.delete(entity);
    }
    @Override public void deleteAllById(Iterable<? extends Long> longs) {
        repo.deleteAllById(longs);
    }
    @Override public void deleteAll(Iterable<? extends Parm> entities) {
        repo.deleteAll(entities);
    }
    @Override public void deleteAll() {
        repo.deleteAll();
    }
}
