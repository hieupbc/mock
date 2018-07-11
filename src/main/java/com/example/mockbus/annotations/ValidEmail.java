package com.example.mockbus.annotations;

import com.example.mockbus.constraints.CustomEmailValidator;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target(value = ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CustomEmailValidator.class)
@Documented
public @interface ValidEmail {

  String message() default "Invalid Email Form";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
