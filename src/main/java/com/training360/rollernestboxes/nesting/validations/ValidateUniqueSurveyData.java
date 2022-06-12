package com.training360.rollernestboxes.nesting.validations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Constraint(validatedBy = UniqueSurveyDataValidator.class)
public @interface ValidateUniqueSurveyData {

    String message() default "You can insert only one survey on the same day at the same nest box by the same observer";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
