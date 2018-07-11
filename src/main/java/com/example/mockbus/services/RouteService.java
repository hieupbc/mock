package com.example.mockbus.services;

import java.util.List;

import com.example.mockbus.entities.RouteDomain;

public interface RouteService extends BaseService<RouteDomain, Integer>{
	/**
	 * find route by departure and destination id
	 * @param from id of departure {@link com.example.mockbus.entities.StationDomain}
	 * @param to id of destination {@link com.example.mockbus.entities.StationDomain}
	 * @return
	 */
	List<RouteDomain> findRouteDomainByDepartureAndDestination(int from, int to);
	
	List<RouteDomain> findRouteDomainByDeparture(int from);
}
