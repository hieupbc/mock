package com.example.mockbus.services;

import java.util.List;

import com.example.mockbus.entities.JourneyDomain;
import com.example.mockbus.entities.TicketDomain;
import com.example.mockbus.entities.UserDomain;

public interface TicketService extends BaseService<TicketDomain, Integer> {
	
	List<TicketDomain> findByJourney(JourneyDomain journeyDomain);

	List<TicketDomain> findAllTicketsOfUser(UserDomain userDomain);

	boolean create(List<TicketDomain> ticketDomains);
}
