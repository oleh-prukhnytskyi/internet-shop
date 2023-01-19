package com.olegpruh.internetshop.service;

import com.olegpruh.internetshop.model.Address;
import com.olegpruh.internetshop.model.Customer;
import com.olegpruh.internetshop.model.DeliveryOption;

public interface CustomerService {
    Customer save(Customer customer);

    Customer findByEmail(String email);

    Customer findByUserEmail(String user_email);

    void update(Long id, String firstName, String lastName, String email, String phone, Address address,
                DeliveryOption deliveryOption);
}
