package com.olegpruh.internetshop.service;

import com.olegpruh.internetshop.model.User;

import javax.naming.AuthenticationException;

public interface AuthenticationService {
    User register(String email, String password);

    User login(String login, String password) throws AuthenticationException;
}
