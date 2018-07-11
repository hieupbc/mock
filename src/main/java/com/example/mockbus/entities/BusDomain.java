package com.example.mockbus.entities;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Bus")
public class BusDomain {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "busName", nullable = false, length = 50)
  private String busName;

  @Column(name = "description", nullable = true)
  private String description;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getBusName() {
    return busName;
  }

  public void setBusName(String number) {
    this.busName = number;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BusDomain busDomain = (BusDomain) o;
    return id == busDomain.id && Objects.equals(busName, busDomain.busName)
        && Objects.equals(description, busDomain.description);
  }

  @Override
  public int hashCode() {

    return Objects.hash(id, busName, description);
  }
}
