package com.olegpruh.internetshop.service;

import com.olegpruh.internetshop.model.Address;

import java.util.List;

public interface AddressService {
    Address save(Address address);

    List<Address> findAllByUserEmail(String user_email);

    Address getById(Long id);
}
