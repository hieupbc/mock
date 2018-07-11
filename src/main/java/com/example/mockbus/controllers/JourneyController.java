package com.example.mockbus.controllers;

import com.example.mockbus.DTO.JourneySearchForm;
import com.example.mockbus.entities.JourneyDomain;
import com.example.mockbus.services.JourneyService;
import com.example.mockbus.services.StationService;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

//@Controller
public class JourneyController {

  private JourneyService journeyService;
  private StationService stationService;

  public JourneyController(JourneyService journeyService,
      StationService stationService) {
    this.journeyService = journeyService;
    this.stationService = stationService;
  }

  /**
   * Get journey searching data and return  conditions
   *
   * @param model model  for data response
   * @param journeySearchForm Dto for journey searching, include start point,
   * end point and depart date
   * @param bindingResult check validation conditions
   */
  @GetMapping(value = {"/journey"})
  public String homepost(Model model,
      @Valid @ModelAttribute JourneySearchForm journeySearchForm,
      BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      model.addAttribute("stations", stationService.findAll());
      model.addAttribute("journeySearchForm", journeySearchForm);
      return "booking_station";
    }
    int from = journeySearchForm.getFrom();
    int to = journeySearchForm.getTo();
    String departDateString = journeySearchForm.getDepartDate();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    LocalDate departDate = LocalDate.parse(departDateString, formatter);
    List<JourneyDomain> journeyDomains = journeyService
        .findByDepartDateAndRoute(from, to, departDate);
    model.addAttribute("journeys", journeyDomains);
    return "booking_journey";
  }
}
