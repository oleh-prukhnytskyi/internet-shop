package com.olegpruh.internetshop.controller;

import com.olegpruh.internetshop.model.User;
import com.olegpruh.internetshop.model.dto.UserLoginDto;
import com.olegpruh.internetshop.model.dto.UserRegistrationDto;
import com.olegpruh.internetshop.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import javax.validation.Valid;
import java.util.stream.Collectors;

@Controller
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @GetMapping("/signin")
    public String signin(Model model) {
        model.addAttribute("page_title", "Sign in");
        return "signin";
    }

    @PostMapping("/signup")
    public String register(@ModelAttribute @Valid UserRegistrationDto userRequestDto, Model model) {
        User user = authenticationService.register(userRequestDto.getEmail(), userRequestDto.getPassword());
        model.addAttribute("user", user);
        return "redirect:/signin";
    }

    @PostMapping("/signin")
    public String login(@ModelAttribute @Valid UserLoginDto userLoginDto, Model model)
            throws AuthenticationException {
        User user = authenticationService.login(userLoginDto.getLogin(),
                userLoginDto.getPassword());
        model.addAttribute("user", user);
        return "redirect:/";
    }

    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("page_title", "Create account");
        return "signup";
    }
}
