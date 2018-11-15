package com.sap.pfs.oauth.config;

import com.sap.pfs.oauth.auth.CustomAuthenticationFailureHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author fahad
 * @since 15/03/2017
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http

                //Configuring Form Login
                .formLogin().loginPage("/login").failureHandler(new CustomAuthenticationFailureHandler()).permitAll()

                //Configuring Logout
                .and().logout().logoutUrl("/logout").permitAll()

                .and().authorizeRequests().antMatchers("/api/signup","/api/signup/verify/**").permitAll()

                //Enable authentication for the rest of the end points
                .and().authorizeRequests().anyRequest().authenticated()

                //disable csrf and frame options for H2
                .and().csrf().disable().headers().frameOptions().disable();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean()
            throws Exception {
        return super.authenticationManagerBean();
    }
}
