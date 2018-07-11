package com.example.mockbus.DTO;

import java.time.LocalDate;
import java.time.LocalTime;

public class TicketDto {

  private int id;
  private LocalDate journeyDepartDate;
  private LocalTime journeyDepartTime;
  private String journeyRouteDepartureName;
  private String journeyRouteDestinationName;
  private long userId;
  private boolean status;
  private int seatId;
  private int journeyBusId;
  private int journeyRoutePrice;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public LocalDate getJourneyDepartDate() {
    return journeyDepartDate;
  }

  public void setJourneyDepartDate(LocalDate journeyDepartDate) {
    this.journeyDepartDate = journeyDepartDate;
  }

  public LocalTime getJourneyDepartTime() {
    return journeyDepartTime;
  }

  public void setJourneyDepartTime(LocalTime journeyDepartTime) {
    this.journeyDepartTime = journeyDepartTime;
  }

  public String getJourneyRouteDepartureName() {
    return journeyRouteDepartureName;
  }

  public void setJourneyRouteDepartureName(String journeyRouteDepartureName) {
    this.journeyRouteDepartureName = journeyRouteDepartureName;
  }

  public String getJourneyRouteDestinationName() {
    return journeyRouteDestinationName;
  }

  public void setJourneyRouteDestinationName(
      String journeyRouteDestinationName) {
    this.journeyRouteDestinationName = journeyRouteDestinationName;
  }

  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }

  public boolean isStatus() {
    return status;
  }

  public void setStatus(boolean status) {
    this.status = status;
  }

  public int getSeatId() {
    return seatId;
  }

  public void setSeatId(int seatId) {
    this.seatId = seatId;
  }

  public int getJourneyBusId() {
    return journeyBusId;
  }

  public void setJourneyBusId(int journeyBusId) {
    this.journeyBusId = journeyBusId;
  }

  public int getJourneyRoutePrice() {
    return journeyRoutePrice;
  }

  public void setJourneyRoutePrice(int journeyRoutePrice) {
    this.journeyRoutePrice = journeyRoutePrice;
  }

}
