package com.example.shoop.repo;

import com.example.shoop.model.Picture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PictureService implements PictureRepo {

    @Autowired private final PictureRepo repo;
    public PictureService(PictureRepo repo) { this.repo = repo; }

    @Override
    public <S extends Picture> S save(S entity) {
        return repo.save(entity);
    }

    @Override
    public <S extends Picture> Iterable<S> saveAll(Iterable<S> entities) {
        return repo.saveAll(entities);
    }

    @Override
    public Optional<Picture> findById(Long aLong) {
        return repo.findById(aLong);
    }

    @Override
    public boolean existsById(Long aLong) {
        return repo.existsById(aLong);
    }

    @Override
    public Iterable<Picture> findAll() {
        return repo.findAll();
    }

    @Override
    public Iterable<Picture> findAllById(Iterable<Long> longs) {
        return repo.findAllById(longs);
    }

    @Override
    public long count() {
        return repo.count();
    }

    @Override
    public void deleteById(Long aLong) {
        repo.deleteById(aLong);
    }

    @Override
    public void delete(Picture entity) {
        repo.delete(entity);
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {
        repo.deleteAllById(longs);
    }

    @Override
    public void deleteAll(Iterable<? extends Picture> entities) {
        repo.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        repo.deleteAll();
    }
}
