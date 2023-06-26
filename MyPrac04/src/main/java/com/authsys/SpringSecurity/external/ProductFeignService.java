package com.authsys.SpringSecurity.external;

import com.authsys.SpringSecurity.external.response.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "PRODUCT-SERVICE/api/v1/products")

public interface ProductFeignService {

    @GetMapping()
    public ResponseEntity<List<ProductResponse>> getAllProduct();

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_MANAGER','ROLE_ADMIN')")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable("id") Long productId);
}
