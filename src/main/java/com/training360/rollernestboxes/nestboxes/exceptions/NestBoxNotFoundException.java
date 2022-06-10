package com.training360.rollernestboxes.nestboxes.exceptions;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;

public class NestBoxNotFoundException extends AbstractThrowableProblem {

    public NestBoxNotFoundException(String nestBoxId) {
        super(URI.create("nest-boxes/not-found"),
                "Not found",
                Status.NOT_FOUND,
                String.format("Nest box not found by nest box id: %s", nestBoxId));
    }
}
