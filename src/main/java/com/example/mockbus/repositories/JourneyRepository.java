package com.example.mockbus.repositories;

import com.example.mockbus.entities.JourneyDomain;
import com.example.mockbus.entities.RouteDomain;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JourneyRepository extends
    JpaRepository<JourneyDomain, Integer> {

  Page<JourneyDomain> findAll(Pageable pageable);

  List<JourneyDomain> findJourneyDomainsByDepartDateAndAndRoute(
      LocalDate departDate, RouteDomain routeDomain);

  Page<JourneyDomain> findJourneyDomainsByDepartDateAndAndRoute(
      Pageable pageable, LocalDate departDate, RouteDomain routeDomain);

  List<JourneyDomain> findJourneyDomainsById(int id);
}
