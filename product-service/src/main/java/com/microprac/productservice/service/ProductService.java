package com.microprac.productservice.service;

import com.microprac.productservice.model.ProductRequest;
import com.microprac.productservice.model.ProductResponse;

import java.util.List;

public interface ProductService {

    public ProductResponse addProduct(ProductRequest productRequest);
    public ProductResponse getProductById(Long productId);

    void reduceQuantity(Long productId, long reduceQuantity);

    List<ProductResponse> getAllProduct();
}
