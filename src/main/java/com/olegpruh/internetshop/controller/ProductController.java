package com.olegpruh.internetshop.controller;

import com.olegpruh.internetshop.model.dto.ReviewDto;
import com.olegpruh.internetshop.service.CustomerService;
import com.olegpruh.internetshop.service.ProductService;
import com.olegpruh.internetshop.service.ReviewService;
import com.olegpruh.internetshop.service.WishlistService;
import com.olegpruh.internetshop.util.AuthenticationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.MessageFormat;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;
    private final ReviewService reviewService;
    private final WishlistService wishlistService;
    private final CustomerService customerService;

    @Autowired
    public ProductController(ProductService productService, ReviewService reviewService,
                             WishlistService wishlistService, CustomerService customerService) {
        this.productService = productService;
        this.reviewService = reviewService;
        this.wishlistService = wishlistService;
        this.customerService = customerService;
    }

    @GetMapping("/{id}")
    public String getAll(Model model, @PathVariable Long id) {
        String user_email = AuthenticationUtil.getAuthentication().getName();
        model.addAttribute("wishlist_products", wishlistService.wishlistProductsId(
                AuthenticationUtil.getAuthentication().getName()));
        model.addAttribute("product", productService.get(id));
        model.addAttribute("page_title", "Product");
        model.addAttribute("reviews", reviewService.findAllByProductId(id));
        model.addAttribute("user", AuthenticationUtil.getAuthentication());
        model.addAttribute("averageRating", reviewService.getAverage(id));
        model.addAttribute("reviewStats", reviewService.getReviewsStats(id));
        model.addAttribute("user_reviews", reviewService.getUserReviewsIds(user_email, id));
        model.addAttribute("customer", customerService.findByUserEmail(user_email));
        return "product";
    }

    @PostMapping("/{id}")
    public String addReview(@PathVariable Long id, @ModelAttribute @Valid ReviewDto reviewDto) {
        reviewService.save(id, reviewDto, AuthenticationUtil.getAuthentication().getName());
        return MessageFormat.format("redirect:/product/{0}", id);
    }

    @PostMapping("/validate")
    @ResponseBody
    public List<ObjectError> validateReview(@ModelAttribute @Valid ReviewDto reviewDto,
                                            BindingResult bindingResult) {
        return bindingResult.getAllErrors();
    }

    @GetMapping("/{product_id}/delete/{id}")
    public String deleteReview(@PathVariable Long id, @PathVariable Long product_id) {
        reviewService.deleteByIdAndUserEmail(id, AuthenticationUtil.getAuthentication().getName());
        return "redirect:/product/" + product_id;
    }
}
