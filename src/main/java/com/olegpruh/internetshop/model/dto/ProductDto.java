package com.olegpruh.internetshop.model.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class ProductDto {
    @NotEmpty(message = "Title cannot be empty")
    private String title;
    @DecimalMin(value = "0.00", message = "Price cannot be less than 0")
    @NotNull(message = "Price cannot be empty")
    private String price;
    @NotEmpty(message = "Please upload some images")
    private MultipartFile[] images;
    @NotEmpty(message = "Description cannot be empty")
    private String description;
}
