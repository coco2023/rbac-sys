package com.prac.ecomm.service.impl;

import com.prac.ecomm.entity.Cart;
import com.prac.ecomm.entity.User;
import com.prac.ecomm.enums.ResultEnum;
import com.prac.ecomm.exception.MyException;
import com.prac.ecomm.repository.CartRepository;
import com.prac.ecomm.repository.UserRepository;
import com.prac.ecomm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    CartRepository cartRepository;

    @Override
    public User findOne(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User save(User user) {
        //register
        try {
            User savedUser = userRepository.save(user);

            // initial Cart
            Cart savedCart = cartRepository.save(new Cart(savedUser));
            savedUser.setCart(savedCart);
            return userRepository.save(savedUser);

        } catch (Exception e) {
            throw new MyException(ResultEnum.VALID_ERROR);
        }
    }
}
