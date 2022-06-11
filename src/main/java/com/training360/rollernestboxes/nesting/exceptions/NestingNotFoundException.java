package com.training360.rollernestboxes.nesting.exceptions;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;

public class NestingNotFoundException extends AbstractThrowableProblem {
    public NestingNotFoundException(long id) {
        super(URI.create("nesting/not-found"),
                "Nesting not found",
                Status.NOT_FOUND,
                String.format("Nesting not found by id: %s", id));
    }
}
