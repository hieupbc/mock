package com.example.mockbus.services.Impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mockbus.entities.JourneyDomain;
import com.example.mockbus.entities.RouteDomain;
import com.example.mockbus.repositories.JourneyRepository;
import com.example.mockbus.services.JourneyService;
import com.example.mockbus.services.RouteService;

@Service
public class JourneyServiceImpl extends BaseServiceImpl<JourneyDomain, Integer> implements JourneyService {

	private JourneyRepository journeyRepository;
	private RouteService routeService;

	@Autowired
	public JourneyServiceImpl(JourneyRepository jpaRepository, RouteService routeService) {
		super(jpaRepository);
		this.journeyRepository = jpaRepository;
		this.routeService = routeService;
	}

	public List<JourneyDomain> findAll() {
		return journeyRepository.findAll();
	}

	@Override
	public List<JourneyDomain> findByDepartDateAndRoute(int from, int to, LocalDate departDate) {
		List<JourneyDomain> journeyDomains = new ArrayList<JourneyDomain>();
		List<JourneyDomain> journeyDomains2 = new ArrayList<JourneyDomain>();
		List<RouteDomain> routeDomain = routeService.findRouteDomainByDepartureAndDestination(from, to);
		for (RouteDomain routeDomain2 : routeDomain) {
			journeyDomains2 = journeyRepository.findJourneyDomainsByDepartDateAndAndRoute(departDate, routeDomain2);
			for (JourneyDomain journeyDomains3 : journeyDomains2) {
				journeyDomains.add(journeyDomains3);
			}
		}
		return journeyDomains;
	}

}
