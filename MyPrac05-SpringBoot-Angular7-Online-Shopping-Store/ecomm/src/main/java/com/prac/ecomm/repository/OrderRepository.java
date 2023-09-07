package com.prac.ecomm.repository;

import com.prac.ecomm.entity.OrderMain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderMain, Integer> {
}
