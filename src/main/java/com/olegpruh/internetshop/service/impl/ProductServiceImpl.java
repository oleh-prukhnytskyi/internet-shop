package com.olegpruh.internetshop.service.impl;

import com.olegpruh.internetshop.model.Product;
import com.olegpruh.internetshop.repository.ProductRepository;
import com.olegpruh.internetshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository repository) {
        this.productRepository = repository;
    }

    @Override
    public Product add(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Page<Product> findAll(PageRequest pageRequest) {
        return productRepository.findAll(pageRequest);
    }

    @Override
    public List<Product> getAllBySearchQuery(String query) {
        return productRepository.findAllByTitleContainsIgnoreCase(query);
    }

    @Override
    public Product get(Long id) {
        return productRepository.findById(id).get();
    }
}

