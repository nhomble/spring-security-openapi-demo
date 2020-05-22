# spring security openapi-spec demo
The code was first generated from 

And then we made the following changes
#### 1. Add spring-security-spring-boot-starter to pom
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
    <version>2.3.0.RELEASE</version>
</dependency>
```
#### 2. Map spring-security
```java
    @Bean
    public WebSecurityConfigurerAdapter securityConfigurerAdapter() {
        return new WebSecurityConfigurerAdapter() {
            @Override
            protected void configure(AuthenticationManagerBuilder auth) throws Exception {
                super.configure(auth);
            }

            @Override
            protected void configure(HttpSecurity http) throws Exception {
                http.authorizeRequests()
                        .antMatchers("/v1/**", "/v2/**").permitAll().anyRequest().authenticated().and()
                        .formLogin().loginPage("/login").permitAll().and()
                        .logout().permitAll();
            }

            @Bean
            public UserDetailsService userDetailsService() {
                UserDetails user = User.withUsername("nhomble")
                        .password("supersecret")
                        .roles("read:pets")
                        .build();
                return new InMemoryUserDetailsManager(user);
            }
        };
    }
```
##### Note: there is an in-memory user defined