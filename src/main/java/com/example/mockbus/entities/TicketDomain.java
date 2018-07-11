package com.example.mockbus.entities;

import com.example.mockbus.entities.audit.DateAudit;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Ticket")
public class TicketDomain extends DateAudit {

  /**
   *
   */
  private static final long serialVersionUID = -4296209842296808L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @ManyToOne
  @JoinColumn(name = "journeyId")
  private JourneyDomain journey;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "userId")
  @JsonManagedReference
  @JsonView
  private UserDomain user;

  @Column(name = "status", nullable = true)
  private boolean status;

  @ManyToOne
  @JoinColumn(name = "seatId")
  private SeatDomain seat;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public JourneyDomain getJourney() {
    return journey;
  }

  public void setJourney(JourneyDomain journey) {
    this.journey = journey;
  }

  public UserDomain getUser() {
    return user;
  }

  public void setUser(UserDomain user) {
    this.user = user;
  }

  public boolean getStatus() {
    return status;
  }

  public void setStatus(boolean status) {
    this.status = status;
  }

  public SeatDomain getSeat() {
    return seat;
  }

  public void setSeat(SeatDomain seat) {
    this.seat = seat;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TicketDomain that = (TicketDomain) o;
    return journey == that.journey
        && Objects.equals(seat, that.seat);
  }

  @Override
  public int hashCode() {

    return Objects.hash(journey, seat);
  }
}
