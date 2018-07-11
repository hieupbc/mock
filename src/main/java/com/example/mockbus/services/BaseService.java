package com.example.mockbus.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Service
public interface BaseService<T, ID extends Serializable> {

    public Page<T> findAll(int page, int size);

    public Page<T> findAll(Pageable pageable);

    public Optional<T> find(ID id);

    public List<T> findAll();

    public T create(T newInstance);

    public T update(T updateInstance);

    public void delete(T entity);
}
