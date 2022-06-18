package com.training360.rollernestboxes.nestboxes.exceptions;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;

public class NestBoxNotFoundException extends AbstractThrowableProblem {
    public NestBoxNotFoundException(String nestBoxNumber) {
        super(URI.create("nest-box/nest-box-not-found"),
                "Nest box not found in the database",
                Status.BAD_REQUEST,
                String.format("Nest box with nest box number %s does not exists", nestBoxNumber));
    }
}
