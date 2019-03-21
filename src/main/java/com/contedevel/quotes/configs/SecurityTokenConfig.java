package com.contedevel.quotes.configs;

import com.contedevel.quotes.components.filters.JwtTokenAuthenticationFilter;
import com.contedevel.quotes.components.security.AccessDeniedHandlerImpl;
import com.contedevel.quotes.components.security.AuthenticationEntryPointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityTokenConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtConfig jwtConfig;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling()
                    .authenticationEntryPoint(new AuthenticationEntryPointImpl())
                    .accessDeniedHandler(new AccessDeniedHandlerImpl())
                .and()
                .addFilterAfter(new JwtTokenAuthenticationFilter(jwtConfig),
                        UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                    .antMatchers(HttpMethod.GET, jwtConfig.getUri()).permitAll()
                    .antMatchers(HttpMethod.POST, "/api/v1/users").permitAll()
                    .antMatchers(HttpMethod.GET, "/", "/vue/**", "/api/v1/quotes**").permitAll()
                    .anyRequest().authenticated();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/js/**", "/css/**", "/favicon.ico");
    }
}
