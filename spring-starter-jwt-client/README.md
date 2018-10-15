# Authu Client

## Open Feign
Add feignInterceptor
```java
    @Bean
    public RequestInterceptor feignInterceptor(){
        return new AuthuOpenFeginMvcInterceptor();
    }
```
