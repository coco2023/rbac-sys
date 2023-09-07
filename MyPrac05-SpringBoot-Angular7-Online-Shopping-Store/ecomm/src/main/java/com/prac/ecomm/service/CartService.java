package com.prac.ecomm.service;

import com.prac.ecomm.entity.Cart;
import com.prac.ecomm.entity.ProductInOrder;
import com.prac.ecomm.entity.User;

import java.util.Collection;

public interface CartService {
    Cart getCart(User user);

    void mergeLocalCart(Collection<ProductInOrder> productInOrders, User user);

    void delete(String itemId, User user);

    void checkout(User user);
}
