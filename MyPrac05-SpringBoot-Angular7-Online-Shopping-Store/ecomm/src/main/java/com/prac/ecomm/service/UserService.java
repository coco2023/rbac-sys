package com.prac.ecomm.service;

import com.prac.ecomm.entity.User;

public interface UserService {

    User findOne(String email);

    User save(User user);
}
