package com.example.mockbus.entities;

public enum SeatPlan {
  TYPE_1(new String[]{"ff_ff",
      "ff_ff",
      "ff_ff",
      "ff_ff",
      "ff___",
      "ff_ff",
      "ff_ff",
      "ff_ff",
      "fffff",});

  SeatPlan(String[] plan) {
    this.plan = plan;
  }

  private String[] plan;

  public String[] getPlan() {
    return plan;
  }
}