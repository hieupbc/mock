package com.example.mockbus.DTO;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class TicketChooseDto {

  @Min(1)
  private int journeyId;
  @NotEmpty(message = "Please choose at least 1 seat")
  private int[] selectedSeats;


  public int getJourneyId() {
    return journeyId;
  }

  public void setJourneyId(int journeyId) {
    this.journeyId = journeyId;
  }

  public int[] getSelectedSeats() {
    return selectedSeats;
  }

  public void setSelectedSeats(int[] chooseSeats) {
    this.selectedSeats = chooseSeats;
  }

}
