package com.olegpruh.internetshop.controller;

import com.olegpruh.internetshop.DataInitializer;
import com.olegpruh.internetshop.model.Product;
import com.olegpruh.internetshop.model.dto.ProductDto;
import com.olegpruh.internetshop.service.*;
import com.olegpruh.internetshop.service.mapper.ProductMapper;
import com.olegpruh.internetshop.util.AuthenticationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/profile")
public class ProfileController {
    private static final int PRODUCTS_COUNT = 12;
    private final WishlistService wishlistService;
    private final OrderService orderService;
    private final OrderElementService orderElementService;
    private final ReviewService reviewService;
    private final ProductService productService;
    private final ProductMapper productMapper;
    private final ProductImageService productImageService;

    @Autowired
    public ProfileController(WishlistService wishlistService, OrderService orderService,
                             OrderElementService orderElementService, ReviewService reviewService,
                             ProductService productService, ProductMapper productMapper,
                             ProductImageService productImageService) {
        this.wishlistService = wishlistService;
        this.orderService = orderService;
        this.orderElementService = orderElementService;
        this.reviewService = reviewService;
        this.productService = productService;
        this.productMapper = productMapper;
        this.productImageService = productImageService;
    }

    @GetMapping
    public String user(Model model) {
        String user_email = AuthenticationUtil.getAuthentication().getName();
        model.addAttribute("page_title", "User Profile");
        model.addAttribute("user", AuthenticationUtil.getAuthentication());
        model.addAttribute("page_menu", "account");
        model.addAttribute("orders", orderElementService.getOrderElementsByUser(user_email));
        return "user";
    }

    @GetMapping("/seller")
    public String seller(Model model) {
        model.addAttribute("page_title", "Seller");
        model.addAttribute("user", AuthenticationUtil.getAuthentication());
        return "seller";
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        model.addAttribute("page_title", "Admin");
        model.addAttribute("user", AuthenticationUtil.getAuthentication());
        return "admin";
    }

    @GetMapping("/courier")
    public String courier(Model model) {
        model.addAttribute("page_title", "Courier");
        model.addAttribute("user", AuthenticationUtil.getAuthentication());
        return "courier";
    }

    //***** PROFILE PAGES *****
    @GetMapping("/wishlist")
    public String wishlist(Model model, @RequestParam(defaultValue = "1") Integer page) {
        PageRequest pageRequest = PageRequest.of(page - 1, PRODUCTS_COUNT);
        Page<Product> products = wishlistService.findAllByUserEmail(
                AuthenticationUtil.getAuthentication().getName(), pageRequest);
        model.addAttribute("products", products);
        model.addAttribute("page_title", "Wishlist");
        model.addAttribute("user", AuthenticationUtil.getAuthentication());
        model.addAttribute("page_menu", "wishlist");
        model.addAttribute("reviewsCount", reviewService.getProductsReviewsCount(products));
        model.addAttribute("reviewsAverage", reviewService.getProductsReviewsAverage(products));
        return "user";
    }

    @GetMapping("/transactions")
    public String transactions(Model model) {
        model.addAttribute("page_title", "Transactions");
        model.addAttribute("user", AuthenticationUtil.getAuthentication());
        model.addAttribute("page_menu", "transactions");
        return "user";
    }

    @GetMapping("/settings")
    public String settings(Model model) {
        model.addAttribute("page_title", "Settings");
        model.addAttribute("user", AuthenticationUtil.getAuthentication());
        model.addAttribute("page_menu", "settings");
        return "user";
    }

    @GetMapping("/orders-history")
    public String ordersHistory(Model model) {
        model.addAttribute("page_title", "Orders History");
        model.addAttribute("user", AuthenticationUtil.getAuthentication());
        model.addAttribute("page_menu", "orders-history");
        return "user";
    }

    @GetMapping("/order/cancel/{id}")
    public String cancelOrder(@PathVariable Long id) {
        orderService.deleteById(id);
        return "redirect:/profile";
    }

    @GetMapping("/order/track/{id}")
    public String trackOrder(Model model, @PathVariable Long id) {
        model.addAttribute("page_title", "Track Order");
        model.addAttribute("user", AuthenticationUtil.getAuthentication());
        model.addAttribute("page_menu", "track");
        model.addAttribute("order", orderElementService.getOrderElementById(id));
        return "user";
    }

    @GetMapping("/seller/add")
    public String sellerAddItem(Model model) {
        model.addAttribute("page_title", "Add Item");
        model.addAttribute("user", AuthenticationUtil.getAuthentication());
        return "/profile-pages/add_item";
    }

    @PostMapping("/seller/add/validate")
    @ResponseBody
    public List<ObjectError> validateNewItem(@ModelAttribute @Valid ProductDto productDto,
                                             BindingResult bindingResult) {
        return bindingResult.getAllErrors();
    }

    @PostMapping("/seller/add")
    public String addNewProduct(@ModelAttribute ProductDto productDto) {
        Product product = productMapper.productDtoToProduct(productDto);
        product.setImages(productImageService.saveImages(productDto.getImages()));
        productService.add(product);
        return "redirect:/profile/seller";
    }
}
