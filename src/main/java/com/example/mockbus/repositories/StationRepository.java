package com.example.mockbus.repositories;

import com.example.mockbus.entities.StationDomain;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StationRepository extends
    JpaRepository<StationDomain, Integer> {

}
