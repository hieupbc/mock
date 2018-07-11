package com.example.mockbus.controllers;

import com.example.mockbus.entities.RouteDomain;
import com.example.mockbus.entities.StationDomain;
import com.example.mockbus.services.RouteService;
import com.example.mockbus.services.StationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

//@Controller
public class RouteController {

  private StationService stationService;
  private RouteService routeService;

  public RouteController(StationService stationService,
      RouteService routeService) {
    this.stationService = stationService;
    this.routeService = routeService;
  }

  /**
   * get a list of destinations of routes with selected depature id
   *
   * @param departure id of depature station
   */
  @GetMapping("/destSelectOption")
  @ResponseBody
  public String home2(Model model, @RequestParam("departure") int departure) {
    model.addAttribute("stations", stationService.findAll());
    List<RouteDomain> routeDomains = routeService
        .findRouteDomainByDeparture(departure);
    List<StationDomain> destinations = new ArrayList<StationDomain>();
    for (RouteDomain routeDomain : routeDomains) {
      model.addAttribute("destination", routeDomain.getDestination().getName());
      destinations.add(routeDomain.getDestination());
    }

    ObjectMapper mapper = new ObjectMapper();
    String destJson = "";
    try {
      destJson = mapper.writeValueAsString(destinations);
      System.out.println(destJson);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
    return destJson;
  }
}
