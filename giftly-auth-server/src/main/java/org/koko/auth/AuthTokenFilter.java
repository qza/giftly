package org.koko.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthTokenFilter extends GenericFilterBean {

    private static final String AUTH_HEADER_NAME = "X-AUTH-TOKEN";

    private final AuthTokenService authTokenService;

    @Autowired
    public AuthTokenFilter(AuthTokenService authTokenService) {
        this.authTokenService = authTokenService;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String authHeader = request.getHeader(AUTH_HEADER_NAME);
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new ServletException("not authorized");
        }
        String token = authHeader.substring(7); // The part after "Bearer "
        request.setAttribute("claims", authTokenService.detectClaims(token));
        filterChain.doFilter(request, response);
    }
}
