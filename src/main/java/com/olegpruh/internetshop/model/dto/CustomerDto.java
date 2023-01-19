package com.olegpruh.internetshop.model.dto;

import com.olegpruh.internetshop.validation.Email;
import com.olegpruh.internetshop.validation.Phone;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class CustomerDto {
    @NotEmpty(message = "First name cannot be empty")
    private String firstName;
    @NotEmpty(message = "Last name cannot be empty")
    private String lastName;
    @Phone
    private String phone;
    @Email
    private String email;
}
