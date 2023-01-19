package com.olegpruh.internetshop.service;

import com.olegpruh.internetshop.model.ProductImage;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductImageService {
    ProductImage save(ProductImage productImage);

    List<ProductImage> saveImages(MultipartFile[] images);
}
