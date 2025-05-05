package com.djw.DemaLibrary.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.Year;

public class PublicationYearValidator implements ConstraintValidator<PastOrPresentYear, Integer> {

    @Override
    public boolean isValid(Integer year, ConstraintValidatorContext constraintValidatorContext) {
        if (year == null)
            return false;
        int currentYear = Year.now().getValue();
        return year <= currentYear;
    }

    @Override
    public void initialize(PastOrPresentYear constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
}
