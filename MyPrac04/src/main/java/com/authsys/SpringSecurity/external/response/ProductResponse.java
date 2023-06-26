package com.authsys.SpringSecurity.external.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ProductResponse {

    private Long productId;

    private String productName;

    private Long price;

    private Long quantity;

}
