package com.example.mockbus.services.Impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.mockbus.entities.JourneyDomain;
import com.example.mockbus.entities.TicketDomain;
import com.example.mockbus.entities.UserDomain;
import com.example.mockbus.repositories.TicketRepository;
import com.example.mockbus.services.TicketService;
import com.example.mockbus.services.UserService;

@Service
public class TicketServiceImpl extends BaseServiceImpl<TicketDomain, Integer> implements TicketService {

	TicketRepository ticketRepository;
	UserService userService;

	public TicketServiceImpl(TicketRepository jpaRepository, UserService userService) {
		super(jpaRepository);
		this.ticketRepository = jpaRepository;
		this.userService = userService;
	}

	@Override
	public List<TicketDomain> findByJourney(JourneyDomain journeyDomain) {
		return ticketRepository.findByJourney(journeyDomain);
	}

	@Override
	public List<TicketDomain> findAllTicketsOfUser(UserDomain userDomain) {
		return ticketRepository.findByUser(userDomain);
	}

	@Override
	@Transactional
	public boolean create(List<TicketDomain> ticketDomains) {
		int payValue = ticketDomains.stream().mapToInt(ticketDomain -> ticketDomain.getJourney().getRoute().getPrice())
				.sum();
		if (userService.makePayment(ticketDomains.get(0).getUser(), payValue)) {
			ticketDomains.forEach(this::create);
			return true;
		}
		return false;
	}

}
