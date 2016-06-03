package com.ashnab.kotoby.config;

import com.ashnab.kotoby.services.SimpleSocialUsersDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;


@Configuration
@EnableWebMvcSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.
                jdbcAuthentication()
                .dataSource(dataSource);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers().httpStrictTransportSecurity().xssProtection()
                .and().authorizeRequests().anyRequest().fullyAuthenticated()
                .and().csrf().disable();

        http.httpBasic().authenticationEntryPoint(authenticationEntryPoint());
        http.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint());

        /*http
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login/authenticate")
                .failureUrl("/login?param.error=bad_credentials")
                .permitAll()
                .and()
                .authorizeRequests()
                .antMatchers("/favicon.ico", "/static-resources/**").permitAll()
                .antMatchers("/**").authenticated()
                .and()
                .rememberMe()
                .and()
                .apply(new SpringSocialConfigurer());*/
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return (request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
    }

    @Bean
    public SocialUserDetailsService socialUsersDetailService() {
        return new SimpleSocialUsersDetailService(userDetailsService());
    }
}