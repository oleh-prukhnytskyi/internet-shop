package com.olegpruh.internetshop.repository;

import com.olegpruh.internetshop.model.DeliveryOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryOptionRepository extends JpaRepository<DeliveryOption, Long> {
}
