package com.prac.ecomm.service.impl;

import com.prac.ecomm.entity.ProductCategory;
import com.prac.ecomm.enums.ResultEnum;
import com.prac.ecomm.exception.MyException;
import com.prac.ecomm.repository.ProductCategoryRepository;
import com.prac.ecomm.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    ProductCategoryRepository productCategoryRepository;

    @Override
    public ProductCategory findByCategoryType(Integer categoryType) {
        ProductCategory res = productCategoryRepository.findByCategoryType(categoryType);
        if(res == null) throw new MyException(ResultEnum.CATEGORY_NOT_FOUND);
        return res;
    }
}
