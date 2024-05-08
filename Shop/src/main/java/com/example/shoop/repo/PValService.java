package com.example.shoop.repo;

import com.example.shoop.model.PValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PValService implements PValRepo {

    @Autowired
    private final PValRepo repo;

    public PValService(PValRepo repo) { this.repo = repo; }

    @Override public <S extends PValue> S save(S entity) {return repo.save(entity);}
    @Override public <S extends PValue> Iterable<S> saveAll(Iterable<S> entities) {return repo.saveAll(entities);}
    @Override public Optional<PValue> findById(KeyProdParam keyProdParam) {return repo.findById(keyProdParam);}
    @Override public boolean existsById(KeyProdParam keyProdParam) {return repo.existsById(keyProdParam);}
    @Override public Iterable<PValue> findAll() {return repo.findAll();}
    @Override public Iterable<PValue> findAllById(Iterable<KeyProdParam> keyProdParams) {return repo.findAllById(keyProdParams);}
    @Override public long count() {return repo.count();}
    @Override public void deleteById(KeyProdParam keyProdParam) {repo.deleteById(keyProdParam);}
    @Override public void delete(PValue entity) {repo.delete(entity);}
    @Override public void deleteAllById(Iterable<? extends KeyProdParam> keyProdParams) {repo.deleteAllById(keyProdParams);}
    @Override public void deleteAll(Iterable<? extends PValue> entities) {repo.deleteAll(entities);}
    @Override public void deleteAll() {repo.deleteAll();}
}
