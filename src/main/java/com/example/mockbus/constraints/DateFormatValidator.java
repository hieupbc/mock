package com.example.mockbus.constraints;

import com.example.mockbus.annotations.ValidLocalDate;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DateFormatValidator implements
    ConstraintValidator<ValidLocalDate, String> {

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    try {
      LocalDate selectedDate = LocalDate.parse(value, formatter);
      LocalDate now = LocalDate.now();
      return selectedDate.isAfter(now);
    } catch (Exception e) {
      return false;
    }
  }

}
