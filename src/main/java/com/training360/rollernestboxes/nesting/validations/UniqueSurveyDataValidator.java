package com.training360.rollernestboxes.nesting.validations;

import com.training360.rollernestboxes.nesting.NestingRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueSurveyDataValidator implements ConstraintValidator<ValidateUniqueSurveyData, String> {

    private NestingRepository repository;

    public UniqueSurveyDataValidator(NestingRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return false;
    }

    @Override
    public void initialize(ValidateUniqueSurveyData constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
}
