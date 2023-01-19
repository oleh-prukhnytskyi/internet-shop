package com.olegpruh.internetshop.service.impl;

import com.olegpruh.internetshop.model.Address;
import com.olegpruh.internetshop.repository.AddressRepository;
import com.olegpruh.internetshop.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public Address save(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public List<Address> findAllByUserEmail(String user_email) {
        return addressRepository.findAllByUserEmail(user_email);
    }

    @Override
    public Address getById(Long id) {
        return addressRepository.getById(id);
    }
}
