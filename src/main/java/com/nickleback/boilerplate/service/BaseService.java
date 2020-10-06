package com.nickleback.boilerplate.service;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public class BaseService<T> {

    private final JpaRepository<T, Long> repository;

    public BaseService(JpaRepository<T, Long> repository){
        this.repository = repository;
    }

    public Optional<T> getById(Long id){
        return repository.findById(id);
    }

    public List<T> getAll(){
        return repository.findAll();
    }

    public void deleteById(Long id){
        repository.deleteById(id);
    }

    public void delete(T entity){
        repository.delete(entity);
    }

    public T save(T entity){
        return repository.save(entity);
    }

}
