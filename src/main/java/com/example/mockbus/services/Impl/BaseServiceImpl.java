package com.example.mockbus.services.Impl;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.example.mockbus.services.BaseService;

public class BaseServiceImpl<T, ID extends Serializable> implements BaseService<T, ID> {
	protected JpaRepository<T, ID> genericRepository;

	public BaseServiceImpl(JpaRepository<T, ID> jpaRepository) {
		this.genericRepository = jpaRepository;
	}

	public JpaRepository<T, ID> getgenericRepository() {
		return genericRepository;
	}

	public void setGenericRepository(JpaRepository<T, ID> genericRepository) {
		this.genericRepository = genericRepository;
	}

	@Override
	public Page<T> findAll(int page, int size) {

		return this.findAll(PageRequest.of(page, size));
	}

	@Override
	public Page<T> findAll(Pageable pageable) {
		return genericRepository.findAll(pageable);
	}

	@Override
	public Optional<T> find(ID id) {
		Optional<T> optionalT = genericRepository.findById(id);
		return optionalT;
	}

	@Override
	public List<T> findAll() {
		return genericRepository.findAll();
	}

	@Transactional
	@Override
	public T create(T newInstance) {
		T t = genericRepository.save(newInstance);
		return (T) t;
	}

	@Transactional
	@Override
	public T update(T updateInstance) {
		return (T) genericRepository.save(updateInstance);
	}

	@Transactional
	@Override
	public void delete(T entity) {
		genericRepository.delete(entity);
	}

}