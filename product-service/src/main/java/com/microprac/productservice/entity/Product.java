package com.microprac.productservice.entity;

import com.microprac.productservice.model.ProductResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name = "products"
)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "price")
    private Long price;

    @Column(name = "quantity")
    private Long quantity;

    public ProductResponse toProductResponse(){
        return ProductResponse.builder()
                .productId(getProductId())
                .productName(getProductName())
                .price(getPrice())
                .quantity(getQuantity())
                .build();
    }

}
