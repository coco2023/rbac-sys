package com.prac.ecomm.service;

import com.prac.ecomm.entity.ProductInOrder;
import com.prac.ecomm.entity.User;

public interface ProductInOrderService {
    void update(String itemId, Integer quantity, User user);

    ProductInOrder findOne(String itemId, User user);
}
