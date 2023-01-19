package com.olegpruh.internetshop.validation.validator;

import com.olegpruh.internetshop.validation.Review;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ReviewValidator implements ConstraintValidator<Review, String> {
    private static final int MIN_LENGTH = 1;
    private static final int MAX_LENGTH = 2000;

    @Override
    public boolean isValid(String text, ConstraintValidatorContext constraintValidatorContext) {
        if (text == null) {
            return false;
        }
        return text.length() <= MAX_LENGTH && text.length() >= MIN_LENGTH;
    }
}
