package com.training360.rollernestboxes.nestboxes.validations.beanvalidations;

import com.training360.rollernestboxes.nestboxes.repository.NestBoxRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NestBoxIdExistsValidator implements ConstraintValidator<ValidateNestBoxIdExists, String> {

    private NestBoxRepository repository;

    public NestBoxIdExistsValidator(NestBoxRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean isValid(String nestBoxNumber, ConstraintValidatorContext constraintValidatorContext) {
        boolean validSize = !nestBoxNumber.isBlank() && nestBoxNumber.strip().length() <= 10;
        return validSize && repository.existsNestBoxByNestBoxNumber(nestBoxNumber);
    }

    @Override
    public void initialize(ValidateNestBoxIdExists constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
}
