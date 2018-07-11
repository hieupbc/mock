package com.example.mockbus.entities;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Seat")
public class SeatDomain {

  @Id
  private int id;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SeatDomain that = (SeatDomain) o;
    return id == that.id;
  }

  @Override
  public int hashCode() {

    return Objects.hash(id);
  }
}
