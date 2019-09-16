package com.sap.pfs.oauth.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationSuccessEventListener implements ApplicationListener<AuthenticationSuccessEvent> {

    @Autowired
    private LoginAttemptService loginAttemptService;

    public void onApplicationEvent(AuthenticationSuccessEvent event) {
//        User user = (User)event.getAuthentication().getPrincipal();
//        String email = user.getUsername();
//
        String email = (String) event.getAuthentication().getPrincipal();

        loginAttemptService.loginSucceeded(email);
    }
}