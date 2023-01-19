package com.olegpruh.internetshop.service;

import com.olegpruh.internetshop.model.DeliveryOption;

import java.util.List;

public interface DeliveryOptionService {
    DeliveryOption save(DeliveryOption deliveryOption);

    List<DeliveryOption> findAll();

    DeliveryOption getById(Long id);
}
