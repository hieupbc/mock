package com.example.mockbus.constraints;

import com.example.mockbus.annotations.UniqueEmail;
import com.example.mockbus.services.UserService;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class EmailUniqueValidator implements
    ConstraintValidator<UniqueEmail, String> {

  @Autowired
  UserService userService;

  public void initialize(UniqueEmail constraint) {
  }

  public boolean isValid(String obj, ConstraintValidatorContext context) {
    return !userService.isExist(obj);
  }
}
