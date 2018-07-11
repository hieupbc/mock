package com.example.mockbus.converters;

import com.example.mockbus.DTO.TicketDto;
import com.example.mockbus.entities.TicketDomain;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TicketDomainToTicketDto implements Converter<TicketDomain, TicketDto> {
	@Autowired
	ModelMapper modelMapper;

	@Override
	public TicketDto convert(TicketDomain ticketDomain) {
		TicketDto ticketDto = modelMapper.map(ticketDomain, TicketDto.class);
		// ticketDto.setDepartDate(ticketDomain.getJourney().getDepartDate());
		// ticketDto.setDepartTime(ticketDomain.getJourney().getDepartTime());
		// ticketDto.setDeparture(ticketDomain.getJourney().getRoute().getDeparture().getName());
		// ticketDto.setDestination(ticketDomain.getJourney().getRoute().getDestination().getName());
		// ticketDto.setDuration(ticketDomain.getJourney().getRoute().getDuration());
		// ticketDto.setPrice(ticketDomain.getJourney().getRoute().getPrice());

		return ticketDto;
	}
}
