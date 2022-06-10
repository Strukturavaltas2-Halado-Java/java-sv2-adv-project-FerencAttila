package com.training360.rollernestboxes.nestboxes.exceptions;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;

public class InvalidConditionException extends AbstractThrowableProblem {

    public InvalidConditionException() {
        super(URI.create("nest-boxes/invalid-condition"),
                "Invalid condition",
                Status.BAD_REQUEST,
                "If expiration date is null or empty, condition cannot be expired!" +
                        "If expiration date is present, condition must be expired!");
    }
}
