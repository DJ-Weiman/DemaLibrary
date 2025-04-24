package com.djw.DemaLibrary.exception;

import jakarta.annotation.Nullable;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;

import java.util.Map;

public class ProblemDetailExt extends ProblemDetail {

    public static ProblemDetail forStatusDetailsAndErrors(final HttpStatusCode status, @Nullable final String detail, final Map<String, String> errors){
        final var problemDetails = ProblemDetail.forStatusAndDetail(status, detail);
        problemDetails.setProperty("errors", errors);
        return problemDetails;
    }
}
