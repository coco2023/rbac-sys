package com.rbacAuth.springSecurityJWT.constant;

public class SecurityConstants {

    // System WHITELIST
    public static final String[] SYSTEM_WHITELIST = {
        "/api/users/login",
        "/api/users/register"
    };

    // JWT token defaults
    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "authSys ";
    public static final String TOKEN_TYPE = "JWT";

    /**
     * JWT签名密钥硬编码到应用程序代码中，应该存放在环境变量或.properties文件中。
     */
    public static final String JWT_SECRET_KEY = "C*F-JaNdRgUkXn2r5u8x/A?D(G+KbPeShVmYq3s6v9y$B&E)H@McQfTjWnZr4u7w";

    /**
     * 角色的key
     **/
    public static final String ROLE_CLAIMS = "rol";

}
