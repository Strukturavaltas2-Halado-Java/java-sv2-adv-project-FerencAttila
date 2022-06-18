package com.training360.rollernestboxes.nestboxes.exceptions;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;

public class CannotDeleteNestBoxException extends AbstractThrowableProblem {
    public CannotDeleteNestBoxException(String nestBoxNumber) {
        super(URI.create("nest-boxes/cannot-delete-nest-box"),
                "Can not delete nest box",
                Status.BAD_REQUEST,
                String.format("Nest box with number %s has nests, thus it can not be deleted", nestBoxNumber));
    }
}
