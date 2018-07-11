package com.example.mockbus.repositories;

import com.example.mockbus.entities.RouteDomain;
import com.example.mockbus.entities.StationDomain;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RouteRepository extends JpaRepository<RouteDomain, Integer> {

  List<RouteDomain> findRouteDomainByDepartureAndDestination(StationDomain from,
      StationDomain to);

  List<RouteDomain> findRouteDomainByDeparture(StationDomain from);
}
