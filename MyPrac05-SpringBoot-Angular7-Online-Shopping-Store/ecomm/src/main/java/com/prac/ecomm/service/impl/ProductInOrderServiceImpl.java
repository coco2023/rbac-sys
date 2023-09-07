package com.prac.ecomm.service.impl;

import com.prac.ecomm.entity.ProductInOrder;
import com.prac.ecomm.entity.User;
import com.prac.ecomm.repository.ProductInOrderRepository;
import com.prac.ecomm.service.ProductInOrderService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Service
@Log4j2
public class ProductInOrderServiceImpl implements ProductInOrderService {

    @Autowired
    private ProductInOrderRepository productInOrderRepository;

    @Override
    public void update(String itemId, Integer quantity, User user) {
        Optional<ProductInOrder> op = Optional.of(new ProductInOrder());
        log.info("user: " + user.toString());
        if (user.getCart() != null) {
            op = user.getCart()
                    .getProducts().stream()
                    .filter(
                            e -> itemId.equals(e.getProductId())
                    ).findFirst();
        }
        log.info("op: " + op);
        op.ifPresent(productInOrder -> {
            productInOrder.setCount(quantity);
            productInOrderRepository.save(productInOrder);
        });
    }

    @Override
    public ProductInOrder findOne(String itemId, User user) {
        var op = user.getCart().getProducts().stream()
                .filter(
                        e -> itemId.equals(e.getProductId())
                ).findFirst();

        AtomicReference<ProductInOrder> res = new AtomicReference<>();
        op.ifPresent(res::set);
        return res.get();
    }
}
