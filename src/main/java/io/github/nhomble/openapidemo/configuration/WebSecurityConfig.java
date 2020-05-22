package io.github.nhomble.openapidemo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.function.Function;

/**
 * using example from https://spring.io/guides/gs/securing-web/
 */
@Configuration
@EnableWebSecurity
// enable preauthorize
@EnableGlobalMethodSecurity(prePostEnabled = true, proxyTargetClass = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    // assign auth paths
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated().and()
                .formLogin().loginPage("/login").permitAll().and()
                .csrf().disable()
                .logout().permitAll();
    }

    // ignore swagger
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**",
                "/v2/api-docs/**",
                "/swagger-resources/**",
                "/configuration/**",
                "/webjars/**");
        super.configure(web);
    }

    // provide a user for testing locally
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withUsername("nhomble")
                .password("{noop}supersecret")
                .authorities("read:pets")
                .build();
        return new InMemoryUserDetailsManager(user);
    }
}
