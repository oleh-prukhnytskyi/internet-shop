package com.olegpruh.internetshop.service;

import com.olegpruh.internetshop.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface ProductService {
    Product add(Product product);

    Page<Product> findAll(PageRequest pageRequest);

    List<Product> getAllBySearchQuery(String query);

    Product get(Long id);
}
