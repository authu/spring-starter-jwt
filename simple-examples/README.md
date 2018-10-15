# Test Project

Eureka server and admin server is disabled jwt, I'm not know how to secure it without login.   
Disable setting:
```properties
authu.jwt.request.filter-enabled=false
```

## How to generate token
For test, need token to request. You can generateToken through Junit4 in `spring-starter-jwt-core`
