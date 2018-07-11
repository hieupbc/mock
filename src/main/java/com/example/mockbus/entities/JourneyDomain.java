package com.example.mockbus.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Journey")
public class JourneyDomain {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @ManyToOne
  @JoinColumn(name = "routeId", nullable = false)
  private RouteDomain route;


  @ManyToOne
  @JoinColumn(name = "bus", nullable = false)
  private BusDomain bus;

  @Column(name = "depart_time", nullable = false)
  private LocalTime departTime;

  @Column(name = "depart_date", nullable = false)
  private LocalDate departDate;

  @JsonIgnore
  @OneToMany(mappedBy = "journey")
  Set<TicketDomain> tickets;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public RouteDomain getRoute() {
    return route;
  }

  public void setRoute(RouteDomain route) {
    this.route = route;
  }

  public BusDomain getBus() {
    return bus;
  }

  public void setBus(BusDomain bus) {
    this.bus = bus;
  }

  public LocalTime getDepartTime() {
    return departTime;
  }

  public void setDepartTime(LocalTime departTime) {
    this.departTime = departTime;
  }

  public Set<TicketDomain> getTickets() {
    return tickets;
  }

  public void setTickets(Set<TicketDomain> tickets) {
    this.tickets = tickets;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    JourneyDomain that = (JourneyDomain) o;
    return id == that.id && route == that.route && bus == that.bus && Objects
        .equals(departTime, that.departTime);
  }

  @Override
  public int hashCode() {

    return Objects.hash(id, route, bus, departTime);
  }

  public LocalDate getDepartDate() {
    return departDate;
  }

  public void setDepartDate(LocalDate departDate) {
    this.departDate = departDate;
  }
}
