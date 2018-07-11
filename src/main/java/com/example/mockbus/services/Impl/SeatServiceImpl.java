package com.example.mockbus.services.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mockbus.entities.JourneyDomain;
import com.example.mockbus.entities.SeatDomain;
import com.example.mockbus.entities.TicketDomain;
import com.example.mockbus.repositories.SeatRepository;
import com.example.mockbus.repositories.TicketRepository;
import com.example.mockbus.services.SeatService;

@Service
public class SeatServiceImpl extends BaseServiceImpl<SeatDomain, Integer> implements SeatService {

	TicketRepository ticketRepository;
	SeatRepository seatRepository;

	@Autowired
	public SeatServiceImpl(SeatRepository jpaRepository, TicketRepository ticketRepository ) {
		super(jpaRepository);
		this.seatRepository = jpaRepository;
		this.ticketRepository = ticketRepository;
	}

	@Override
	public List<SeatDomain> getAvailableSeats(JourneyDomain journeyDomain) {
		List<SeatDomain> seats = seatRepository.findAll();
		List<SeatDomain> bookedSeats = this.getBookedSeats(journeyDomain);
		bookedSeats.forEach(seat->seats.remove(seat));
		return seats;
	}

	@Override
	public List<SeatDomain> getBookedSeats(JourneyDomain journeyDomain) {
		List<TicketDomain> bookedTicket = ticketRepository.findByJourney(journeyDomain);
		List<SeatDomain> bookedSeat = new ArrayList<SeatDomain>();
		bookedTicket.forEach(ticket -> bookedSeat.add(ticket.getSeat()));
		return bookedSeat;
	}

}
