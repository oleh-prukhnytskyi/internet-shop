package com.olegpruh.internetshop.controller;

import com.olegpruh.internetshop.service.WishlistService;
import com.olegpruh.internetshop.util.AuthenticationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/profile/wishlist")
public class WishlistController {
    private final WishlistService wishlistService;

    @Autowired
    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @GetMapping("/add/{id}")
    public String add(@PathVariable Long id) {
        wishlistService.save(AuthenticationUtil.getAuthentication().getName(), id);
        return "redirect:/profile/wishlist";
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable Long id) {
        wishlistService.deleteByUserEmailAndProductId(AuthenticationUtil.getAuthentication().getName(), id);
        return "redirect:/profile/wishlist";
    }
}
