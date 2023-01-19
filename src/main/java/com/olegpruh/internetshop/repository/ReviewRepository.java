package com.olegpruh.internetshop.repository;

import com.olegpruh.internetshop.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findAllByProductId(Long product_id);

    @Transactional
    void deleteByIdAndUserEmail(Long id, String user_email);

    List<Review> findAllByUserEmailAndProductId(String user_email, Long product_id);
}
