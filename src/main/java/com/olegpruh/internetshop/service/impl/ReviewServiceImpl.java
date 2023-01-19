package com.olegpruh.internetshop.service.impl;

import com.olegpruh.internetshop.model.Product;
import com.olegpruh.internetshop.model.Review;
import com.olegpruh.internetshop.model.User;
import com.olegpruh.internetshop.model.dto.ReviewDto;
import com.olegpruh.internetshop.repository.ReviewRepository;
import com.olegpruh.internetshop.service.ProductService;
import com.olegpruh.internetshop.service.ReviewService;
import com.olegpruh.internetshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ProductService productService;
    private final UserService userService;
    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewServiceImpl(ProductService productService, UserService userService,
                             ReviewRepository reviewRepository) {
        this.productService = productService;
        this.userService = userService;
        this.reviewRepository = reviewRepository;
    }

    @Override
    public Review save(Long product_id, ReviewDto reviewDto, String user_email) {
        User user = userService.findByEmail(user_email).get();
        Product product = productService.get(product_id);
        return reviewRepository.save(new Review(reviewDto.getText(), user, product,
                reviewDto.getRating(), reviewDto.getName()));
    }

    @Override
    public List<Review> findAllByProductId(Long product_id) {
        return reviewRepository.findAllByProductId(product_id);
    }

    @Override
    public BigDecimal getAverage(Long product_id) {
        return BigDecimal.valueOf(reviewRepository.findAllByProductId(product_id)
                .stream()
                .map(Review::getRating)
                .mapToInt(Integer::intValue)
                .average().orElse(0))
                .setScale(1, RoundingMode.HALF_UP);
    }

    @Override
    public Map<Integer, Long> getReviewsStats(Long product_id) {
        List<Review> allByProductId = reviewRepository.findAllByProductId(product_id);
        Map<Integer, Long> map = new HashMap<>();
        for (int i = 5; i > 0; i--) {
            int finalI = i;
            map.put(i, allByProductId.stream().filter(r -> r.getRating()== finalI).count());
        }
        return map;
    }

    @Override
    public void deleteByIdAndUserEmail(Long id, String user_email) {
        reviewRepository.deleteByIdAndUserEmail(id, user_email);
    }

    @Override
    public List<Long> getUserReviewsIds(String user_email, Long product_id) {
        return reviewRepository.findAllByUserEmailAndProductId(user_email, product_id)
                .stream()
                .map(Review::getId)
                .collect(Collectors.toList());
    }

    @Override
    public Map<Long, Integer> getProductsReviewsCount(Page<Product> products) {
        Map<Long, Integer> reviews = new HashMap<>();
        for (Product product : products) {
            reviews.put(product.getId(), findAllByProductId(product.getId()).size());
        }
        return reviews;
    }

    @Override
    public Map<Long, BigDecimal> getProductsReviewsAverage(Page<Product> products) {
        Map<Long, BigDecimal> reviews = new HashMap<>();
        for (Product product : products) {
            reviews.put(product.getId(), getAverage(product.getId()));
        }
        return reviews;
    }
}
