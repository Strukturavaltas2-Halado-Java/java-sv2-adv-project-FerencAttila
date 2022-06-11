package com.training360.rollernestboxes.nestboxes.validations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Constraint(validatedBy = NestBoxIdNotExistsValidator.class)
public @interface ValidateNestBoxIdNotExists {

    String message() default "Nest box id must be unique, cannot be null or empty string and consists maximum of 10 characters!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
