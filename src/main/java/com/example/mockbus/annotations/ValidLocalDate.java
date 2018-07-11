package com.example.mockbus.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import com.example.mockbus.constraints.DateFormatValidator;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = DateFormatValidator.class)
@Documented
public @interface ValidLocalDate {

  String message() default "Please enter a valid date";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
