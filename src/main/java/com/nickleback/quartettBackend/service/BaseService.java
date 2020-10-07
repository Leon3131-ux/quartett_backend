package com.nickleback.quartettBackend.service;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public class BaseService<T, R> {

    private final JpaRepository<T, R> repository;

    public BaseService(JpaRepository<T, R> repository){
        this.repository = repository;
    }

    public Optional<T> getById(R id){
        return repository.findById(id);
    }

    public List<T> getAll(){
        return repository.findAll();
    }

    public void deleteById(R id){
        repository.deleteById(id);
    }

    public void delete(T entity){
        repository.delete(entity);
    }

    public T save(T entity){
        return repository.save(entity);
    }

}
