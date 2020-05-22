# spring security openapi-spec demo
The code was first generated from 

And then we made the following changes
#### 1. Add spring-security-spring-boot-starter to pom
Pulled in newer versions of spring libs
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
    <version>2.2.2.RELEASE</version>
</dependency>
```
#### 2. Map spring-security
In [here](src/main/java/io/github/nhomble/openapidemo/configuration/WebSecurityConfig.java)
##### Note: there is an in-memory user defined

# Demo
1. refresh the swagger page
2. authenticate as nhomble:supersecret
3. try and fail the delete pet api, 403
4. try and succeed the get pet by id, 2xx