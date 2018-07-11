package com.example.mockbus.exceptions;

import org.springframework.validation.BindingResult;

public class ValidationFailedException extends RuntimeException {

  /**
   *
   */
  private static final long serialVersionUID = 1L;
  private BindingResult bindingResult;

  public ValidationFailedException(BindingResult bindingResult) {
    this.bindingResult = bindingResult;
  }

  public BindingResult getBindingResult() {
    return bindingResult;
  }
}
