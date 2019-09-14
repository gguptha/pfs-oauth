package com.sap.pfs.oauth.auth;

import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException
            exception) throws IOException, ServletException {
        //super.onAuthenticationFailure(request, response, exception);
        if (exception.getCause() != null && exception.getCause().getClass().isAssignableFrom(LockedException.class))
            response.sendRedirect("/auth/login?locked");
        else
            response.sendRedirect("/auth/login?error");
    }
}