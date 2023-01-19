package com.olegpruh.internetshop.service.impl;

import com.olegpruh.internetshop.model.ProductImage;
import com.olegpruh.internetshop.repository.ProductImageRepository;
import com.olegpruh.internetshop.service.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

@Service
public class ProductImageServiceImpl implements ProductImageService {
    private final ProductImageRepository productImageRepository;
    @Value("${products.images.path}")
    private String IMAGES_PATH;

    @Autowired
    public ProductImageServiceImpl(ProductImageRepository productImageRepository) {
        this.productImageRepository = productImageRepository;
    }

    @Override
    public ProductImage save(ProductImage productImage) {
        return productImageRepository.save(productImage);
    }

    @Override
    public List<ProductImage> saveImages(MultipartFile[] images) {
        List<ProductImage> productImages = new ArrayList<>();
        Arrays.stream(images).forEach(file -> {
            try {
                byte[] bytes = file.getBytes();
                String fileName = new StringBuilder().append(UUID.randomUUID()).append(".")
                        .append(file.getOriginalFilename().substring(file.getOriginalFilename()
                                .lastIndexOf(".") + 1)).toString();
                Files.write(Paths.get(IMAGES_PATH + fileName), bytes);
                productImages.add(save(new ProductImage(fileName)));
            } catch (IOException e) {
                throw new RuntimeException("Can't add images", e);
            }
        });
        return productImages;
    }
}
