package com.training360.rollernestboxes.nestboxes.validations;

import com.training360.rollernestboxes.nestboxes.NestBoxRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NestBoxIdNotExistsValidator implements ConstraintValidator<ValidateNestBoxIdNotExists, String> {

    private NestBoxRepository repository;

    public NestBoxIdNotExistsValidator(NestBoxRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean isValid(String nestBoxId, ConstraintValidatorContext constraintValidatorContext) {
        boolean validSize = !nestBoxId.isBlank() && nestBoxId.strip().length() <= 10;
        return validSize && !repository.existsNestBoxByNestBoxId(nestBoxId);
    }

    @Override
    public void initialize(ValidateNestBoxIdNotExists constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
}
