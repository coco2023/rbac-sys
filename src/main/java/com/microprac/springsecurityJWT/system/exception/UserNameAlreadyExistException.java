package com.microprac.springsecurityJWT.system.exception;

import com.microprac.springsecurityJWT.system.constant.ErrorCode;

import java.util.Map;

public class UserNameAlreadyExistException extends BaseException {

    public UserNameAlreadyExistException(Map<String, Object> data) {
        super(ErrorCode.USER_NAME_ALREADY_EXIST, data);
    }
}
