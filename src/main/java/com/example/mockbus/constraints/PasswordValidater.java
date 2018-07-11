package com.example.mockbus.constraints;

import com.example.mockbus.DTO.UserRegisterForm;
import com.example.mockbus.annotations.PasswordMatches;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidater implements
    ConstraintValidator<PasswordMatches, UserRegisterForm> {

  public void initialize(PasswordMatches constraint) {
  }

  @Override
  public boolean isValid(UserRegisterForm userRegisterForm,
      ConstraintValidatorContext constraintValidatorContext) {
    return userRegisterForm.getPassword()
        .equals(userRegisterForm.getRetypedPassword());
  }
}
