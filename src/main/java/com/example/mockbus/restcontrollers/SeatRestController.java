package com.example.mockbus.restcontrollers;

import com.example.mockbus.entities.SeatPlan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SeatRestController {

  @GetMapping("/test")
  public ResponseEntity getSeatPlan() {
    String[] plan = SeatPlan.TYPE_1.getPlan();
    return new ResponseEntity<String[]>(plan, HttpStatus.OK);
  }
}
