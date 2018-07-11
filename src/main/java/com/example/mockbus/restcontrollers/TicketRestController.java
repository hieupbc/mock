package com.example.mockbus.restcontrollers;

import com.example.mockbus.DTO.TicketChooseDto;
import com.example.mockbus.DTO.TicketDto;
import com.example.mockbus.entities.JourneyDomain;
import com.example.mockbus.entities.SeatDomain;
import com.example.mockbus.entities.TicketDomain;
import com.example.mockbus.entities.UserDomain;
import com.example.mockbus.exceptions.ResourceNotFoundException;
import com.example.mockbus.exceptions.ResponseMsg;
import com.example.mockbus.exceptions.ValidationFailedException;
import com.example.mockbus.services.JourneyService;
import com.example.mockbus.services.SeatService;
import com.example.mockbus.services.TicketService;
import com.example.mockbus.services.UserService;
import com.example.mockbus.utils.Utils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.validation.Valid;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")
public class TicketRestController {

  private UserService userService;
  private TicketService ticketService;
  private JourneyService journeyService;
  private SeatService seatService;
  private Converter<TicketDomain, TicketDto> ticketToTicketDto;
  private Utils utils;

  public TicketRestController(
      Converter<TicketDomain, TicketDto> ticketToTicketDto,
      UserService userService,
      TicketService ticketService, SeatService seatService,
      JourneyService journeyService, Utils utils) {
    this.userService = userService;
    this.ticketService = ticketService;
    this.seatService = seatService;
    this.journeyService = journeyService;
    this.ticketToTicketDto = ticketToTicketDto;
    this.utils = utils;
  }

  @RequestMapping(value = "/tickets/user/{id}", method = RequestMethod.GET)
  @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_EMPLOYEE')")
  public ResponseEntity<Object> getTicketsByUser(@PathVariable("id") long id) {
    boolean hasPermission = utils.hasPermission(id, "ROLE_EMPLOYEE");
    if (!hasPermission) {
      return ResponseEntity.status(HttpStatus.FORBIDDEN)
          .body(new ResponseMsg("DENIED", "DO NOT HAVE PERMISSION"));
    }
    Optional<UserDomain> userDomain = userService.find(id);
    if (!userDomain.isPresent()) {
      throw new ResourceNotFoundException("Accound ID: " + id);
    }
    List<TicketDomain> allTicketsOfUser = ticketService
        .findAllTicketsOfUser(userDomain.get());
    List<TicketDto> ticketDtos = new ArrayList<>();
    allTicketsOfUser
        .forEach(ticket -> ticketDtos.add(ticketToTicketDto.convert(ticket)));
    return ResponseEntity.ok().body(ticketDtos);

  }

  @RequestMapping(value = "/tickets", method = RequestMethod.POST)
  public ResponseEntity<Object> bookTicket(
      @Valid @RequestBody TicketChooseDto ticketChooseDto,
      BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      throw new ValidationFailedException(bindingResult);
    }
    int journeyId = ticketChooseDto.getJourneyId();
    Optional<JourneyDomain> journeyDomain = journeyService.find(journeyId);
    if (!journeyDomain.isPresent()) {
      throw new ResourceNotFoundException("Journey ID " + journeyId);
    }
    int[] selectedSeatIDs = ticketChooseDto.getSelectedSeats();
    Set<SeatDomain> selectedSeatDomains = new HashSet<>();
    for (int i = 0; i < selectedSeatIDs.length; i++) {
      Optional<SeatDomain> seat = seatService.find(selectedSeatIDs[i]);
      if (!seat.isPresent()) {
        throw new ResourceNotFoundException("Seat ID " + i);
      }
      selectedSeatDomains.add(seat.get());
    }
    List<SeatDomain> bookedSeats = seatService
        .getBookedSeats(journeyDomain.get());
    if (!Collections.disjoint(selectedSeatDomains, bookedSeats)) {
      throw new ResourceNotFoundException("Your Request");
    }
    Authentication authentication = SecurityContextHolder.getContext()
        .getAuthentication();
    UserDomain userDomain = userService.findByEmail(authentication.getName())
        .get();

    List<TicketDomain> selectedTickets = new ArrayList<>();
    for (SeatDomain seatDomain : selectedSeatDomains) {
      TicketDomain ticketDomain = new TicketDomain();
      ticketDomain.setJourney(journeyDomain.get());
      ticketDomain.setSeat(seatDomain);
      ticketDomain.setUser(userDomain);
      ticketDomain.setStatus(true);
      selectedTickets.add(ticketDomain);
    }
    ticketService.create(selectedTickets);
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(new ResponseMsg("Status", "Success!"));
  }
}
