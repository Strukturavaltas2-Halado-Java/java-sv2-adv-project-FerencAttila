package com.training360.rollernestboxes.nestboxes.exceptions;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;

public class NestBoxAlreadyExpiredException extends AbstractThrowableProblem {

    public NestBoxAlreadyExpiredException(String nestBoxId) {
        super(URI.create("nest-boxes/nest-box-already-expired"),
                "Nest box already expired",
                Status.BAD_REQUEST,
                String.format("Nest box with id: %s already expired!", nestBoxId));
    }
}
