package com.example.shoop.repo;

import com.example.shoop.model.UA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UAService implements UARepo {

    @Autowired private final UARepo uaRepo;
    public UAService(UARepo uaRepo) { this.uaRepo = uaRepo; }


    @Override  public Optional<UA> findByUA_ID(String email) { return uaRepo.findByUA_ID(email);  }




    @Override
    public <S extends UA> S save(S entity) {
        return uaRepo.save(entity);
    }

    @Override
    public <S extends UA> Iterable<S> saveAll(Iterable<S> entities) {
        return uaRepo.saveAll(entities);
    }

    @Override
    public Optional<UA> findById(Long aLong) {
        return uaRepo.findById(aLong);
    }

    @Override
    public boolean existsById(Long aLong) {
        return uaRepo.existsById(aLong);
    }

    @Override
    public Iterable<UA> findAll() {
        return uaRepo.findAll();
    }

    @Override
    public Iterable<UA> findAllById(Iterable<Long> longs) {
        return uaRepo.findAllById(longs);
    }

    @Override
    public long count() {
        return uaRepo.count();
    }

    @Override
    public void deleteById(Long aLong) {
        uaRepo.deleteById(aLong);
    }

    @Override
    public void delete(UA entity) {
        uaRepo.delete(entity);
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {
        uaRepo.deleteAllById(longs);
    }

    @Override
    public void deleteAll(Iterable<? extends UA> entities) {
        uaRepo.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        uaRepo.deleteAll();
    }
}
