package com.example.shoop.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ParamService implements ParamRepo {

    @Autowired
    private final ParamRepo repo;

    public ParamService(ParamRepo repo) { this.repo = repo; }

    @Override public <S extends Param> S save(S entity) {return repo.save(entity);}
    @Override public <S extends Param> Iterable<S> saveAll(Iterable<S> entities) {return repo.saveAll(entities);}
    @Override public Optional<Param> findById(KeyProdParam keyProdParam) {return repo.findById(keyProdParam);}
    @Override public boolean existsById(KeyProdParam keyProdParam) {return repo.existsById(keyProdParam);}
    @Override public Iterable<Param> findAll() {return repo.findAll();}
    @Override public Iterable<Param> findAllById(Iterable<KeyProdParam> keyProdParams) {return repo.findAllById(keyProdParams);}
    @Override public long count() {return repo.count();}
    @Override public void deleteById(KeyProdParam keyProdParam) {repo.deleteById(keyProdParam);}
    @Override public void delete(Param entity) {repo.delete(entity);}
    @Override public void deleteAllById(Iterable<? extends KeyProdParam> keyProdParams) {repo.deleteAllById(keyProdParams);}
    @Override public void deleteAll(Iterable<? extends Param> entities) {repo.deleteAll(entities);}
    @Override public void deleteAll() {repo.deleteAll();}
}
