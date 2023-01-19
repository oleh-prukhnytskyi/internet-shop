package com.olegpruh.internetshop.service.impl;

import com.olegpruh.internetshop.model.Address;
import com.olegpruh.internetshop.model.Customer;
import com.olegpruh.internetshop.model.DeliveryOption;
import com.olegpruh.internetshop.repository.CustomerRepository;
import com.olegpruh.internetshop.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer findByEmail(String email) {
        return customerRepository.findByEmail(email);
    }

    @Override
    public Customer findByUserEmail(String user_email) {
        return customerRepository.findByUserEmail(user_email);
    }

    @Override
    public void update(Long id, String firstName, String lastName, String email, String phone, Address address,
                       DeliveryOption deliveryOption) {
        customerRepository.update(id, firstName, lastName, email, phone, address, deliveryOption);
    }
}
