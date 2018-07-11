package com.example.mockbus.services;

import java.time.LocalDate;
import java.util.List;

import com.example.mockbus.entities.JourneyDomain;

public interface JourneyService extends BaseService<JourneyDomain, Integer> {
	/**
	 * Find Journeys by departure, destination and depature date
	 * @param from id of depature @{@link com.example.mockbus.entities.StationDomain}
	 * @param to if of destination {@link com.example.mockbus.entities.StationDomain}
	 * @param departureTime depature date
	 * @return
	 */
	List<JourneyDomain> findByDepartDateAndRoute(int from, int to, LocalDate departureTime);
}
