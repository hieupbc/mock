package com.example.mockbus.services.Impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.mockbus.entities.StationDomain;
import com.example.mockbus.repositories.StationRepository;
import com.example.mockbus.services.StationService;

@Service
public class StationServiceImpl extends BaseServiceImpl<StationDomain, Integer> implements StationService {
	private StationRepository stationRepository;

	public StationServiceImpl(StationRepository stationRepository) {
		super(stationRepository);
		this.stationRepository = stationRepository;
	}


	@Override
	public List<StationDomain> findAll() {
		return stationRepository.findAll();
	}

}
