package com.olegpruh.internetshop.service.mapper;

import com.olegpruh.internetshop.model.UserSettings;
import com.olegpruh.internetshop.model.dto.UserSettingsDto;
import org.springframework.stereotype.Component;

@Component
public class UserSettingsMapper implements DtoMapper<UserSettingsDto, UserSettings> {
    @Override
    public UserSettings mapToModel(UserSettingsDto dto) {
        UserSettings userSettings = new UserSettings();
        userSettings.setUser(dto.getUser());
        userSettings.setListLayout(dto.getListLayout());
        return userSettings;
    }
}
