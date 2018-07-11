package com.example.mockbus.services;

import java.util.List;

import com.example.mockbus.entities.JourneyDomain;
import com.example.mockbus.entities.SeatDomain;

public interface SeatService extends BaseService<SeatDomain, Integer> {

	List<SeatDomain> getAvailableSeats(JourneyDomain journeyDomain);
	List<SeatDomain> getBookedSeats(JourneyDomain journeyDomain);


}
