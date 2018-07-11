package com.example.mockbus.repositories;

import com.example.mockbus.entities.BusDomain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusRepository extends JpaRepository<BusDomain, Integer> {

  Page<BusDomain> findAll(Pageable pageable);
}
