package com.training360.rollernestboxes.nesting.exceptions;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;
import java.time.LocalDate;

public class InvalidSurveyDateException extends AbstractThrowableProblem {

    public InvalidSurveyDateException(LocalDate surveyDate, String comparison) {
        super(URI.create("nesting/nesting-in-nest-box-not-alive"),
                "Nesting in nest boy not alive",
                Status.BAD_REQUEST,
                String.format("Survey date %s was %s nest box was alive", surveyDate, comparison));
    }
}
