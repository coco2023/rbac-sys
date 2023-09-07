package com.prac.ecomm.service.impl;

import com.prac.ecomm.entity.Cart;
import com.prac.ecomm.entity.OrderMain;
import com.prac.ecomm.entity.ProductInOrder;
import com.prac.ecomm.entity.User;
import com.prac.ecomm.enums.ResultEnum;
import com.prac.ecomm.exception.MyException;
import com.prac.ecomm.repository.CartRepository;
import com.prac.ecomm.repository.OrderRepository;
import com.prac.ecomm.repository.ProductInOrderRepository;
import com.prac.ecomm.service.CartService;
import com.prac.ecomm.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductInOrderRepository productInOrderRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductService productService;

    @Override
    public Cart getCart(User user) {
        return user.getCart();
    }

    @Override
    @Transactional
    public void mergeLocalCart(Collection<ProductInOrder> productInOrders, User user) {
        Cart finalCart = user.getCart();
        productInOrders.forEach(productInOrder -> {
            Set<ProductInOrder> set = finalCart.getProducts();
            Optional<ProductInOrder> old = set.stream()
                    .filter(
                            e -> e.getProductId().equals((productInOrder.getProductId()))
                    ).findFirst();
            ProductInOrder product;
            if (old.isPresent()) {
                product = old.get();
                product.setCount(productInOrder.getCount() + product.getCount());
            } else {
                product = productInOrder;
                product.setCart(finalCart);
                finalCart.getProducts().add(product);
            }
            productInOrderRepository.save(product);
        });
        cartRepository.save(finalCart);
    }

    @Override
    public void delete(String itemId, User user) {
        if(itemId.equals("") || user == null) {
            throw new MyException(ResultEnum.ORDER_STATUS_ERROR);
        }

        var op = user.getCart().getProducts().stream().filter(e -> itemId.equals(e.getProductId())).findFirst();
        op.ifPresent(productInOrder -> {
            productInOrder.setCart(null);
            productInOrderRepository.deleteById(productInOrder.getId());
        });
    }

    @Override
    @Transactional
    public void checkout(User user) {
        // Creat an order
        OrderMain order = new OrderMain(user);
        orderRepository.save(order);

        // clear cart's foreign key & set order's foreign key& decrease stock
        user.getCart().getProducts().forEach(productInOrder -> {
            productInOrder.setCart(null);
            productInOrder.setOrderMain(order);
            productService.decreaseStock(productInOrder.getProductId(), productInOrder.getCount());
            productInOrderRepository.save(productInOrder);
        });
    }

}
