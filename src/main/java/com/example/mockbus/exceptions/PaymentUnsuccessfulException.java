package com.example.mockbus.exceptions;

public class PaymentUnsuccessfulException extends RuntimeException {

  /**
   *
   */
  private static final long serialVersionUID = 1L;

  public PaymentUnsuccessfulException(String message) {
    super(message);
  }

}
