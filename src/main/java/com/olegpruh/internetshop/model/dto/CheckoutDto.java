package com.olegpruh.internetshop.model.dto;

import com.olegpruh.internetshop.validation.Email;
import com.olegpruh.internetshop.validation.Phone;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class CheckoutDto {
    @NotEmpty(message = "First name cannot be empty")
    private String firstName;
    @NotEmpty(message = "Last name cannot be empty")
    private String lastName;
    @Phone
    private String phone;
    @Email
    private String email;
    @NotEmpty(message = "Address cannot be empty")
    private String address;
    private String city;
    @NotEmpty(message = "House cannot be empty")
    private String house;
    @NotEmpty(message = "Postal code cannot be empty")
    private String postcode;
    private boolean saveAddress;
    private String addressOption;
    private Long shipping;
}
