package com.olegpruh.internetshop.service.impl;

import com.olegpruh.internetshop.model.DeliveryOption;
import com.olegpruh.internetshop.repository.DeliveryOptionRepository;
import com.olegpruh.internetshop.service.DeliveryOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryOptionServiceImpl implements DeliveryOptionService {
    private final DeliveryOptionRepository deliveryOptionRepository;

    @Autowired
    public DeliveryOptionServiceImpl(DeliveryOptionRepository deliveryOptionRepository) {
        this.deliveryOptionRepository = deliveryOptionRepository;
    }

    @Override
    public DeliveryOption save(DeliveryOption deliveryOption) {
        return deliveryOptionRepository.save(deliveryOption);
    }

    @Override
    public List<DeliveryOption> findAll() {
        return deliveryOptionRepository.findAll();
    }

    @Override
    public DeliveryOption getById(Long id) {
        return deliveryOptionRepository.getById(id);
    }
}
