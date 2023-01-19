package com.olegpruh.internetshop.controller;

import com.olegpruh.internetshop.service.CartService;
import com.olegpruh.internetshop.service.WishlistService;
import com.olegpruh.internetshop.util.AuthenticationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;
    private final WishlistService wishlistService;

    @Autowired
    public CartController(CartService service, WishlistService wishlistService) {
        this.cartService = service;
        this.wishlistService = wishlistService;
    }

    @GetMapping
    public String cart(Model model) {
        model.addAttribute("wishlist_products", wishlistService.wishlistProductsId(
                AuthenticationUtil.getAuthentication().getName()));
        model.addAttribute("products", cartService.findAllByUserEmail(
                AuthenticationUtil.getAuthentication().getName()));
        model.addAttribute("page_title", "My cart");
        model.addAttribute("user", AuthenticationUtil.getAuthentication());
        return "cart";
    }

    @GetMapping("/add/{id}")
    public String add(@PathVariable Long id) {
        cartService.save(AuthenticationUtil.getAuthentication().getName(), id);
        return "redirect:/cart";
    }

    @GetMapping("/remove-all/{id}")
    public String removeAll(@PathVariable Long id) {
        cartService.deleteCartByUserEmailAndProductId(
                AuthenticationUtil.getAuthentication().getName(), id);
        return "redirect:/cart";
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable Long id) {
        cartService.remove(
                AuthenticationUtil.getAuthentication().getName(), id);
        return "redirect:/cart";
    }

    @GetMapping("/move/{id}")
    public String move(@PathVariable Long id) {
        cartService.save(
                AuthenticationUtil.getAuthentication().getName(), id);
        wishlistService.deleteByUserEmailAndProductId(AuthenticationUtil.getAuthentication().getName(), id);
        return "redirect:/cart";
    }
}
