package com.example.mockbus.controllers;

import com.example.mockbus.DTO.JourneySearchForm;
import com.example.mockbus.DTO.UserRegisterForm;
import com.example.mockbus.entities.JourneyDomain;
import com.example.mockbus.exceptions.ResourceNotFoundException;
import com.example.mockbus.services.JourneyService;
import com.example.mockbus.services.StationService;
import java.util.Optional;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

//@Controller
public class HomeController {

  private StationService stationService;
  private JourneyService journeyService;

  public HomeController(StationService stationService,
      JourneyService journeyService) {
    this.stationService = stationService;
    this.journeyService = journeyService;
  }

  /**
   * Page for bus booking
   */
  @GetMapping(value = {"/bookingform"})
  public String home(Model model) {
    JourneySearchForm journeySearchForm = new JourneySearchForm();
    model.addAttribute("stations", stationService.findAll());
    model.addAttribute("journeySearchForm", journeySearchForm);
    return "booking_station";
  }


  @GetMapping("/login")
  public String login() {
    return "login";
  }

  @GetMapping("/403")
  public String accessDenied() {
    return "403";
  }

  /**
   * get page for registration
   */
  @GetMapping("/register")
  public String signUp(Model model) {
    model.addAttribute("userRegisterForm", new UserRegisterForm());
    return "register";
  }

  /**
   * dash board for admins and employees
   */
  @GetMapping("/dashboard")
  @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
  public String getDashboard() {
    return "employee/dashboard";
  }

  /**
   * redirect for 404 error
   */
  @GetMapping("/404")
  public String error() {
    return "404";
  }

  /**
   * redirect for 500 error
   */
  @GetMapping("/500")
  public String error500() {
    return "500";
  }

  /**
   * get home page
   */
  @GetMapping(value = {"/", "/home"})
  public String homePage() {
    return "home";
  }

  /**
   * Get seat selection page from idJourney
   *
   * @param idJourney id of selected journey
   */
  @GetMapping("/selectseat")
  @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_EMPLOYEE')")
  public String getSeatPlan(@RequestParam int idJourney, Model model) {
    Optional<JourneyDomain> journeyDomain = journeyService.find(idJourney);
    if (!journeyDomain.isPresent()) {
      throw new ResourceNotFoundException("Journey ID " + idJourney);
    }
    int price = journeyDomain.get().getRoute().getPrice();
    model.addAttribute("price", price);
    model.addAttribute("idJourney", idJourney);
    return "/user/seatplan";
  }
}
