package com.example.shoop.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChildrenInlineService implements ChildrenInlineRepo {

    @Autowired
    private final ChildrenInlineRepo childrenInlineRepo;

    public ChildrenInlineService(ChildrenInlineRepo childrenInlineRepo) {
        this.childrenInlineRepo = childrenInlineRepo;
    }


    @Override public <S extends ChildrenInline> S save(S entity) {return childrenInlineRepo.save(entity);}
    @Override public <S extends ChildrenInline> Iterable<S> saveAll(Iterable<S> entities) {return childrenInlineRepo.saveAll(entities);}
    @Override public Optional<ChildrenInline> findById(LongLong longLong) {return childrenInlineRepo.findById(longLong);}
    @Override public boolean existsById(LongLong longLong) {return childrenInlineRepo.existsById(longLong);}
    @Override public Iterable<ChildrenInline> findAll() {return childrenInlineRepo.findAll();}
    @Override public Iterable<ChildrenInline> findAllById(Iterable<LongLong> longLongs) {return childrenInlineRepo.findAllById(longLongs);}
    @Override public long count() {return childrenInlineRepo.count();}
    @Override public void deleteById(LongLong longLong) {childrenInlineRepo.deleteById(longLong);}
    @Override public void delete(ChildrenInline entity) {childrenInlineRepo.delete(entity);}
    @Override public void deleteAllById(Iterable<? extends LongLong> longLongs) {childrenInlineRepo.deleteAllById(longLongs);}
    @Override public void deleteAll(Iterable<? extends ChildrenInline> entities) {childrenInlineRepo.deleteAll(entities);}
    @Override public void deleteAll() {childrenInlineRepo.deleteAll();}
}
