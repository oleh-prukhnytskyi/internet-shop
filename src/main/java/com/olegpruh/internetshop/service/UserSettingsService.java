package com.olegpruh.internetshop.service;

import com.olegpruh.internetshop.model.User;
import com.olegpruh.internetshop.model.UserSettings;

public interface UserSettingsService {
    UserSettings save(String layout, String user_email);

    void update(String listLayout, Long user_email);

    UserSettings findByUserEmailOrSession(String user_email, String session);

    UserSettings findBySession(String session);

    UserSettings findByUserEmail(String user_email);

    void updateUser(User user, Long id);

    void deleteById(Long id);
}
