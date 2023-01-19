package com.olegpruh.internetshop.service;

import com.olegpruh.internetshop.model.Product;
import com.olegpruh.internetshop.model.Review;
import com.olegpruh.internetshop.model.dto.ReviewDto;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface ReviewService {
    Review save(Long product_id, ReviewDto reviewDto, String user_email);

    List<Review> findAllByProductId(Long product_id);

    BigDecimal getAverage(Long product_id);

    Map<Integer, Long> getReviewsStats(Long product_id);

    void deleteByIdAndUserEmail(Long id, String user_email);

    List<Long> getUserReviewsIds(String user_email, Long product_id);

    Map<Long, Integer> getProductsReviewsCount(Page<Product> products);

    Map<Long, BigDecimal> getProductsReviewsAverage(Page<Product> products);
}
