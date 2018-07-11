package com.example.mockbus.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import com.example.mockbus.constraints.EmailUniqueValidator;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = EmailUniqueValidator.class)
@Documented
public @interface UniqueEmail {

  String message() default " Email already exists";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
