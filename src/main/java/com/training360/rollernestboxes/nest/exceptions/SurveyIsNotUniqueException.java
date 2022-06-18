package com.training360.rollernestboxes.nest.exceptions;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;
import java.time.LocalDate;

public class SurveyIsNotUniqueException extends AbstractThrowableProblem {
    public SurveyIsNotUniqueException(String nestBoxNumber, LocalDate dateOfSurvey, String observer) {
        super(URI.create("nests/survey-is-not-uniqe"),
                "Survey is not unique",
                Status.BAD_REQUEST,
                String.format("There is another observation in the database for nest box number %s on %s by %s", nestBoxNumber, dateOfSurvey, observer));
    }
}
