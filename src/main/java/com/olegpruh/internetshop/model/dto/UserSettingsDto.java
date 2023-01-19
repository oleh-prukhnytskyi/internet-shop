package com.olegpruh.internetshop.model.dto;

import com.olegpruh.internetshop.model.User;
import lombok.Data;

@Data
public class UserSettingsDto {
    private String listLayout;
    private User user;
}
