package com.example.mockbus.restcontrollers;

import com.example.mockbus.DTO.JourneySearchForm;
import com.example.mockbus.entities.JourneyDomain;
import com.example.mockbus.entities.SeatDomain;
import com.example.mockbus.entities.SeatPlan;
import com.example.mockbus.exceptions.ValidationFailedException;
import com.example.mockbus.services.JourneyService;
import com.example.mockbus.services.SeatService;
import com.example.mockbus.utils.Utils;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")
public class JourneyRestController {

  private JourneyService journeyService;
  private SeatService seatService;
  private Utils utils;

  public JourneyRestController(JourneyService journeyService,
      SeatService seatService, Utils utils) {
    this.journeyService = journeyService;
    this.seatService = seatService;
    this.utils = utils;
  }

  @GetMapping("/journey/{idJourney}/booked")
  @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_EMPLOYEE')")
  public ResponseEntity<String[]> getBookedSeat(@PathVariable int idJourney) {
    Optional<JourneyDomain> optional = journeyService.find(idJourney);
    List<SeatDomain> bookedSeats = seatService.getBookedSeats(optional.get());
    String[] bookedSeatResult = new String[bookedSeats.size()];
    int i = 0;
    for (SeatDomain seat : bookedSeats) {
      bookedSeatResult[i++] = utils
          .getPositionFromSeatnumber(seat.getId(), SeatPlan.TYPE_1, '_');
    }
    return ResponseEntity.ok(bookedSeatResult);
  }

  @PostMapping(value = {"/journey"})
  public ResponseEntity homepost(
      @Valid @ModelAttribute JourneySearchForm journeySearchForm,
      BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      throw new ValidationFailedException(bindingResult);
    }
    int from = journeySearchForm.getFrom();
    int to = journeySearchForm.getTo();
    String departDateString = journeySearchForm.getDepartDate();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    LocalDate departDate = LocalDate.parse(departDateString, formatter);
    List<JourneyDomain> journeyDomains = journeyService
        .findByDepartDateAndRoute(from, to, departDate);
    return ResponseEntity.ok(journeyDomains);
  }

}
