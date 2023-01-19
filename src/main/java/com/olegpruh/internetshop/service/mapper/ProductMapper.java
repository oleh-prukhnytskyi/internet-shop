package com.olegpruh.internetshop.service.mapper;

import com.olegpruh.internetshop.model.Product;
import com.olegpruh.internetshop.model.dto.ProductDto;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ProductMapper {
    public Product productDtoToProduct(ProductDto productDto) {
        Product product = new Product();
        product.setTitle(productDto.getTitle());
        product.setPrice(new BigDecimal(productDto.getPrice()));
        product.setDescription(productDto.getDescription());
        return product;
    }
}
