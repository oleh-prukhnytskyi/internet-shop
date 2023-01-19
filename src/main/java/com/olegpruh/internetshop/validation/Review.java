package com.olegpruh.internetshop.validation;

import com.olegpruh.internetshop.validation.validator.ReviewValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ReviewValidator.class)
@Target({ ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Review {
    String message() default "Invalid review length";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
