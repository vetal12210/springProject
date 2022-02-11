package com.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@Component
public class AuthSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse resp,
                                        Authentication authentication) throws IOException {
        Collection<? extends GrantedAuthority> userAuthorities = authentication.getAuthorities();
        String redirectURL = req.getContextPath();

        if (hasRole(userAuthorities, "user")) {
            redirectURL = "/user";
        } else if (hasRole(userAuthorities, "admin")) {
            redirectURL = "/admin";
        }
        resp.sendRedirect(redirectURL);
    }

    private boolean hasRole(Collection<? extends GrantedAuthority> userAuthorities, String roleName) {
        return userAuthorities
                .stream()
                .anyMatch(r -> r.getAuthority().equals(roleName));
    }
}