package com.prac.ecomm.api;

import com.prac.ecomm.entity.OrderMain;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class OrderController {

    @GetMapping("/order")
    public Page<OrderMain> orderList() {
        return null;
    }

    @PatchMapping("/order/cancel/{id}")
    public ResponseEntity<OrderMain> cancel(){
        return null;
    }

    @PatchMapping("/order/finish/{id}")
    public ResponseEntity<OrderMain> finish() {
        return null;
    }

    @GetMapping("/order/{id}")
    public ResponseEntity show() {
        return null;
    }
}
