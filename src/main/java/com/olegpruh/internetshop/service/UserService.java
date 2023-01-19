package com.olegpruh.internetshop.service;

import com.olegpruh.internetshop.model.User;

import java.util.Optional;

public interface UserService {
    User save(User user);

    Optional<User> findByEmail(String email);
}
