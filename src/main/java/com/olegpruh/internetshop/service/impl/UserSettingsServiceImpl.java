package com.olegpruh.internetshop.service.impl;

import com.olegpruh.internetshop.model.User;
import com.olegpruh.internetshop.model.UserSettings;
import com.olegpruh.internetshop.repository.UserSettingsRepository;
import com.olegpruh.internetshop.service.UserService;
import com.olegpruh.internetshop.service.UserSettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.Optional;

@Service
public class UserSettingsServiceImpl implements UserSettingsService {
    private final UserSettingsRepository userSettingsRepository;
    private final UserService userService;

    @Autowired
    public UserSettingsServiceImpl(UserSettingsRepository userSettingsRepository, UserService userService) {
        this.userSettingsRepository = userSettingsRepository;
        this.userService = userService;
    }

    @Override
    public UserSettings save(String layout, String user_email) {
        String session = RequestContextHolder.currentRequestAttributes().getSessionId();
        UserSettings userSettings = new UserSettings();
        userSettings.setListLayout(layout);
        Optional<User> user = userService.findByEmail((user_email));
        if (user.isPresent()) {
            userSettings.setUser(user.get());
        } else {
            userSettings.setSession(session);
        }
        if (findByUserEmailOrSession(user_email, session) != null) {
            userSettingsRepository.update(layout, userSettingsRepository
                    .findByUserEmailOrSession(user_email, session).getId());
            return userSettings;
        } else {
            return userSettingsRepository.save(userSettings);
        }
    }

    @Override
    public void update(String listLayout, Long user_email) {
        userSettingsRepository.update(listLayout, user_email);
    }

    @Override
    public UserSettings findByUserEmailOrSession(String user_email, String session) {
        return userSettingsRepository.findByUserEmailOrSession(user_email, RequestContextHolder
                .currentRequestAttributes().getSessionId());
    }

    @Override
    public UserSettings findBySession(String session) {
        return userSettingsRepository.findBySession(session);
    }

    @Override
    public UserSettings findByUserEmail(String user_email) {
        return userSettingsRepository.findByUserEmail(user_email);
    }

    @Override
    public void updateUser(User user, Long id) {
        userSettingsRepository.updateUser(user, id);
    }

    @Override
    public void deleteById(Long id) {
        userSettingsRepository.deleteById(id);
    }
}
