package com.prac.ecomm.service;

import com.prac.ecomm.entity.OrderMain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public interface OrderService {
    Page<OrderMain> findByBuyerEmail(String email, Pageable pageable);

    Page<OrderMain> findAll(Pageable pageable);

    OrderMain findOne(Long orderId);

    OrderMain finish(Long orderId);

    OrderMain cancel(Long orderId);
}
