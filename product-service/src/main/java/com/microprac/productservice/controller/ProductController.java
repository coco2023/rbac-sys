package com.microprac.productservice.controller;

import com.microprac.productservice.entity.Product;
import com.microprac.productservice.model.ProductRequest;
import com.microprac.productservice.model.ProductResponse;
import com.microprac.productservice.service.ProductService;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/products")
@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponse> addProduct (@RequestBody ProductRequest productRequest) {
        var productResponse = productService.addProduct(productRequest);
        return new ResponseEntity<>(productResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable("id") Long productId) {
        ProductResponse productResponse = productService.getProductById(productId);

        return new ResponseEntity<>(productResponse, HttpStatus.ACCEPTED);
    }

    @GetMapping()
    public ResponseEntity<List<ProductResponse>> getAllProduct() {
        List<ProductResponse> productResponse = productService.getAllProduct();

        return new ResponseEntity<>(productResponse, HttpStatus.ACCEPTED);
    }

    @PutMapping("/reduceQuantity/{id}")
    // http://localhost:9001/api/v1/products/reduceQuantity/2?reduceQuantity=20
    public ResponseEntity<Void> reduceQuantity(@PathVariable("id") Long productId,
                                               @RequestParam long reduceQuantity) {
        productService.reduceQuantity(productId, reduceQuantity);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
