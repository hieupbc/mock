package com.example.mockbus.DTO;

import com.example.mockbus.annotations.ValidLocalDate;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class JourneySearchForm {

  @Min(value = 1, message = "Please choose a valid station")
  private int from;
  @Min(value = 1, message = "Please choose a valid station")
  private int to;
  @NotNull
  @ValidLocalDate
  private String departDate;

  public int getFrom() {
    return from;
  }

  public void setFrom(int from) {
    this.from = from;
  }

  public int getTo() {
    return to;
  }

  public void setTo(int to) {
    this.to = to;
  }

  public String getDepartDate() {
    return departDate;
  }

  public void setDepartDate(String departDate) {
    this.departDate = departDate;
  }

}
