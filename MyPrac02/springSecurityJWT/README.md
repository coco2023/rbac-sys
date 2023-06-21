# Simple Demo for RBAC-Spring Security system

## V1 Realized

- Realize the basic Authorization process to system new users.
- Realize the user register.
- assign roles to users when they register.

### file&function description

1. Springboot part

<p>

    entity: User, Role, UserRole

    controller: UserController
                    | -- user register
                    | -- getAllUser

    service: UserService, UserServiceImpl

    dto: UserRegisterRequest, UserRepresentation

</p>


2. Spring Security part


- **config**: SecurityConfiguration 

  - Overwrite configure to assign authority to different url; 
  
  - Revoke and control filter & exception handler

- **filter**: JwtAuthorizationFilter
                      
  - Invoke **JwtTokenUtils** for the token authority:
    - Help the filter chain to intercept incoming requests and perform JWT validation
    
    - Intercept Request: The filter intercepts incoming requests before they reach the actual endpoint or other security filters

    - Extract JWT: The filter extracts the JWT from the request, typically from the `Authorization` header. 
  
      - `String previousToken = stringRedisTemplate.opsForValue().get(JwtTokenUtils.getId(tokenValue));
`
    - Proceed with Authorization: After the authentication is set, subsequent security filters or the endpoint handler can perform authorization checks based on the authenticated user's roles, authorities, or other attributes.
  
      - `authentication = JwtTokenUtils.getAuthentication(tokenValue);`
    
- **util**: JwtTokenUtils

  - **Validate JWT**: The filter verifies the integrity and validity of the JWT. This includes checking the token's signature, expiration, and any additional claims or custom validations required.
  
    - `public static String getId(String token)`

  - **Set Authentication**: If the JWT is valid, the filter sets the authenticated user's information in the security context. This typically involves creating an instance of Authentication (e.g., `UsernamePasswordAuthenticationToken`)
  
    - `public static UsernamePasswordAuthenticationToken getAuthentication(String token) `

- **constant**: SecurityConstants

- **exception**: JwtAccessDeniedHandler, JwtAuthenticationEntryPoint


