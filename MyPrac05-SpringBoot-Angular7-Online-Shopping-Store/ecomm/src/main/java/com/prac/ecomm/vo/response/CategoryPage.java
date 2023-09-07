package com.prac.ecomm.vo.response;

import com.prac.ecomm.entity.ProductInfo;
import lombok.*;
import org.springframework.data.domain.Page;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class CategoryPage {

    private String category;
    private Page<ProductInfo> page;

}
