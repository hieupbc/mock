package com.example.mockbus.services.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.mockbus.entities.RouteDomain;
import com.example.mockbus.entities.StationDomain;
import com.example.mockbus.repositories.RouteRepository;
import com.example.mockbus.repositories.StationRepository;
import com.example.mockbus.services.RouteService;

@Service
public class RouteServiceImpl extends BaseServiceImpl<RouteDomain, Integer> implements RouteService {

	private StationRepository stationRepository;
	private RouteRepository routeRepository;

	public RouteServiceImpl(RouteRepository routeRepository, StationRepository stationRepository) {
		super(routeRepository);
		this.routeRepository = routeRepository;
		this.stationRepository = stationRepository;
	}

	@Override
	public List<RouteDomain> findRouteDomainByDepartureAndDestination(int from, int to) {
		Optional<StationDomain> stationDept = stationRepository.findById(from);
		Optional<StationDomain> stationDes = stationRepository.findById(to);

		return routeRepository.findRouteDomainByDepartureAndDestination(stationDept.get(), stationDes.get());
	}

	@Override
	public List<RouteDomain> findRouteDomainByDeparture(int from) {
		Optional<StationDomain> stationDept = stationRepository.findById(from);
		return routeRepository.findRouteDomainByDeparture(stationDept.get());
	}
}
