package com.olegpruh.internetshop.security;

import com.olegpruh.internetshop.model.User;
import com.olegpruh.internetshop.model.UserSettings;
import com.olegpruh.internetshop.service.UserService;
import com.olegpruh.internetshop.service.UserSettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;

import static org.springframework.security.core.userdetails.User.withUsername;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserService userService;
    private final UserSettingsService userSettingsService;

    @Autowired
    public CustomUserDetailsService(UserService userService, UserSettingsService userSettingsService) {
        this.userService = userService;
        this.userSettingsService = userSettingsService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByEmail(username).orElseThrow(
                () -> new UsernameNotFoundException("No user found with email " + username));
        String session = RequestContextHolder.currentRequestAttributes().getSessionId();
        if (userSettingsService.findByUserEmail(user.getEmail()) != null
                && userSettingsService.findBySession(session) != null) {
            userSettingsService.deleteById(userSettingsService.findBySession(session).getId());
        } else {
            UserSettings userSettings = userSettingsService.findBySession(session);
            if (userSettings != null) {
                userSettingsService.updateUser(user, userSettings.getId());
            }
        }
        return withUsername(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRoles()
                        .stream()
                        .map(i -> i.getRoleName().name())
                        .toArray(String[]::new))
                .build();
    }
}
