package com.example.mockbus.constraints;

import com.example.mockbus.annotations.ValidEmail;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Value;

public class CustomEmailValidator implements
    ConstraintValidator<ValidEmail, String> {

  @Value("${validate.email}")
  private String EMAIL_REGEX;

  /**
   * Implements the validation logic. The state of {@code value} must not be
   * altered.
   * <p>
   * This method can be accessed concurrently, thread-safety must be ensured by
   * the implementation.
   *
   * @param value object to validate
   * @param context context in which the constraint is evaluated
   * @return {@code false} if {@code value} does not pass the constraint
   */
  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    Pattern pattern = Pattern.compile(EMAIL_REGEX);
    Matcher matcher = pattern.matcher(value);
    return matcher.matches();
  }
}
