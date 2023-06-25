package com.spring4all.constant;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum HttpResponseStatusEnum implements CommonResponse {

    SUCCESS("0", "success"),                            // 成功请求
    FORBIDDEN_OPERATION("2", "can_not_access_need_auth")               // 权限不足
    ;
    private String code;
    private String message;

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Object getResult() {
        return null;
    }

}
