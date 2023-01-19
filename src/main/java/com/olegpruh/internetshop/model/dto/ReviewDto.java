package com.olegpruh.internetshop.model.dto;

import com.olegpruh.internetshop.validation.Review;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;

@Data
public class ReviewDto {
    @Review
    private String text;
    @Range(min = 1, max = 5, message = "Please select a star rating")
    private int rating;
    @NotEmpty(message = "Name cannot be empty")
    private String name;
}
