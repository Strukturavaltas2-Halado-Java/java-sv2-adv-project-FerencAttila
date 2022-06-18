package com.training360.rollernestboxes.nestboxes.validations.beanvalidations;

import com.training360.rollernestboxes.nestboxes.repository.NestBoxRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NestBoxIdDoesNotExistsValidator implements ConstraintValidator<ValidateNestBoxIdDoesNotExists, String> {

    private NestBoxRepository repository;

    public NestBoxIdDoesNotExistsValidator(NestBoxRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean isValid(String nestBoxNumber, ConstraintValidatorContext constraintValidatorContext) {
        boolean validSize = !nestBoxNumber.isBlank() && nestBoxNumber.strip().length() <= 10;
        return validSize && !repository.existsNestBoxByNestBoxNumber(nestBoxNumber);
    }

    @Override
    public void initialize(ValidateNestBoxIdDoesNotExists constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
}
