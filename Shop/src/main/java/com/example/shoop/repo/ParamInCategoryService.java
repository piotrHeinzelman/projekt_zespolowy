package com.example.shoop.repo;

import com.example.shoop.model.ParamInCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ParamInCategoryService implements ParamInCategoryRepo {

    @Autowired
    private final ParamInCategoryRepo paramInCategoryRepo;

    public ParamInCategoryService(ParamInCategoryRepo paramInCategoryRepo) {
        this.paramInCategoryRepo = paramInCategoryRepo;
    }


    @Override public <S extends ParamInCategory> S save(S entity) {return paramInCategoryRepo.save(entity);}
    @Override public <S extends ParamInCategory> Iterable<S> saveAll(Iterable<S> entities) {return paramInCategoryRepo.saveAll(entities);}
    @Override public Optional<ParamInCategory> findById(KeyCatParam keyCatParam) {return paramInCategoryRepo.findById(keyCatParam);}
    @Override public boolean existsById(KeyCatParam keyCatParam) {return paramInCategoryRepo.existsById(keyCatParam);}
    @Override public Iterable<ParamInCategory> findAll() {return paramInCategoryRepo.findAll();}
    @Override public Iterable<ParamInCategory> findAllById(Iterable<KeyCatParam> longLongs) {return paramInCategoryRepo.findAllById(longLongs);}
    @Override public long count() {return paramInCategoryRepo.count();}
    @Override public void deleteById(KeyCatParam keyCatParam) {
        paramInCategoryRepo.deleteById(keyCatParam);}
    @Override public void delete(ParamInCategory entity) {
        paramInCategoryRepo.delete(entity);}
    @Override public void deleteAllById(Iterable<? extends KeyCatParam> longLongs) {
        paramInCategoryRepo.deleteAllById(longLongs);}
    @Override public void deleteAll(Iterable<? extends ParamInCategory> entities) {
        paramInCategoryRepo.deleteAll(entities);}
    @Override public void deleteAll() {
        paramInCategoryRepo.deleteAll();}
}
