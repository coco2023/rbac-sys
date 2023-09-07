package com.prac.ecomm.service;

import com.prac.ecomm.entity.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public interface ProductService {

    ProductInfo findOne(String productId);

    ProductInfo save(ProductInfo productInfo);

    ProductInfo update(ProductInfo productInfo);

    void delete(String productId);

    Page<ProductInfo> findAll(Pageable pageable);

    Page<ProductInfo> findAllInCategory(Integer categoryType, Pageable pageable);

    void decreaseStock(String productId, int amount);

    void increaseStock(String productId, int amount);
}
