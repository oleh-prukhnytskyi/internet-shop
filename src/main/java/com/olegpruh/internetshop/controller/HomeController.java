package com.olegpruh.internetshop.controller;

import com.olegpruh.internetshop.model.Product;
import com.olegpruh.internetshop.service.ProductService;
import com.olegpruh.internetshop.service.ReviewService;
import com.olegpruh.internetshop.service.UserSettingsService;
import com.olegpruh.internetshop.service.WishlistService;
import com.olegpruh.internetshop.service.mapper.UserSettingsMapper;
import com.olegpruh.internetshop.util.AuthenticationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {
    private static final int PRODUCTS_COUNT = 16;
    private final ProductService productService;
    private final WishlistService wishlistService;
    private final UserSettingsService userSettingsService;
    private final ReviewService reviewService;

    @Autowired
    public HomeController(ProductService productService, WishlistService wishlistService,
                          UserSettingsService userSettingsService, ReviewService reviewService) {
        this.productService = productService;
        this.wishlistService = wishlistService;
        this.userSettingsService = userSettingsService;
        this.reviewService = reviewService;
    }

    @GetMapping
    public String findAll(Model model, @RequestParam(defaultValue = "1") Integer page) {
        PageRequest pageRequest = PageRequest.of(page - 1, PRODUCTS_COUNT);
        Page<Product> products = productService.findAll(pageRequest);

        model.addAttribute("wishlist_products", wishlistService.wishlistProductsId(
                AuthenticationUtil.getAuthentication().getName()));
        model.addAttribute("products", products);
        model.addAttribute("page_title", "Home");
        model.addAttribute("user", AuthenticationUtil.getAuthentication());
        model.addAttribute("reviewsCount", reviewService.getProductsReviewsCount(products));
        model.addAttribute("reviewsAverage", reviewService.getProductsReviewsAverage(products));
        model.addAttribute("userSettings", userSettingsService.findByUserEmailOrSession(
                AuthenticationUtil.getAuthentication().getName(),
                RequestContextHolder.currentRequestAttributes().getSessionId()));
        return "home";
    }

    @GetMapping("/search/{query}")
    public String getAll(Model model, @PathVariable String query) {
        model.addAttribute("products", productService.getAllBySearchQuery(query));
        model.addAttribute("page_title", "Search: \""+query+"\"");
        model.addAttribute("user", AuthenticationUtil.getAuthentication());
        model.addAttribute("search_query", query);
        return "home";
    }

    @PostMapping
    public String settings(@RequestParam("listLayout") String layout) {
        userSettingsService.save(layout,
                AuthenticationUtil.getAuthentication().getName());
        return "redirect:/";
    }
}
