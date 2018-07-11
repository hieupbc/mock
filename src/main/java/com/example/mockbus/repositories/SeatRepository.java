package com.example.mockbus.repositories;

import com.example.mockbus.entities.SeatDomain;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<SeatDomain, Integer> {

}
