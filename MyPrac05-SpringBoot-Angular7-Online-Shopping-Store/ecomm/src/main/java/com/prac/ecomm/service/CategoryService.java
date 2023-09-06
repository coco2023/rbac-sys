package com.prac.ecomm.service;

import com.prac.ecomm.entity.ProductCategory;

public interface CategoryService {
    ProductCategory findByCategoryType(Integer categoryType);
}
