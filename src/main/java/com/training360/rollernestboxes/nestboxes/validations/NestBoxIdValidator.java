package com.training360.rollernestboxes.nestboxes.validations;

import com.training360.rollernestboxes.nestboxes.NestBoxRepository;
import com.training360.rollernestboxes.nestboxes.model.NestBox;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class NestBoxIdValidator implements ConstraintValidator<NestBoxId, String> {

    private List<String> nestBoxIds;

    private NestBoxRepository repository;

    public NestBoxIdValidator(NestBoxRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean isValid(String nestBoxId, ConstraintValidatorContext constraintValidatorContext) {
        return nestBoxIds.stream()
                .filter(id -> id.equals(nestBoxId))
                .findFirst()
                .isEmpty();
    }

    @Override
    public void initialize(NestBoxId constraintAnnotation) {
        this.nestBoxIds = repository.findAll().stream()
                .map(NestBox::getNestBoxId)
                .toList();
    }
}
