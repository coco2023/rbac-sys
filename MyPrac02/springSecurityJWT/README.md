# Simple Demo for RBAC-Spring Security system

## V3 
- add logout function

### Testing

1. login

> localhost:9334/api/users/login
> {
"userName": "customer9",
"password": "12345",
> // "telephone": "1234567"
"rememberMe": true
> }

2. logout
localhost:9334/api/users/logout
> **!!!IMPORTANT!!!**   
>       
>    Add {'Key': 'Authorization', 'Value': 'from previous login Authorization header'}
>         
> to the **Headers** of **Logout POST request**


```


## V2 
- add Customers, Suppliers, Admin register method
- add user login method
- add role authority when user login


**com.rbacAuth.springSecurityJWT.service**

```

        // 1.1 get UserRole by userId
        long userId = user.getUserId();
        List<UserRole> userRoles = userRoleRepository.findByUserId(userId);

        // 1.2 get RoleId list from UserRole
        List<Long> roles = userRoles.stream()
                .map(UserRole::getRoleId)
                .collect(Collectors.toList());
        log.info("roleId Table: " + roles.get(0) + ", " + roles.get(1));

        // 1.3 get roleName list from Role
        List<String> roleName = roles.stream()
                .map(role -> roleRepository.findById(role).get().getRoleName())
                .collect(Collectors.toList());

        log.info("roleName Table: " + roleName);

        // 1.4 = 1.2 + 1.3 get Role_Name from UsreRole and Role tables
        List<String> roleNames = userRoles.stream()
                .map(UserRole::getRoleId)
                .map(role -> roleRepository.findById(role).get().getRoleName())
                .collect(Collectors.toList());
        log.info("roleNames List: " + roleNames);

```

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


