package com.shiproutes.shared.infrastructure.auth;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

public class AuthorizationFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;

    public AuthorizationFilter(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
        throws ServletException, IOException {
        String authHeaderValue = request.getHeader("authorization");

        if (authHeaderValue == null || !authHeaderValue.startsWith("Bearer ")) {
            request.setAttribute("jwtInfo", JwtInfo.unknown());
            chain.doFilter(request, response);
            return;
        }

        try {
            String token = authHeaderValue.replace("Bearer ", "");
            JwtInfo jwtInfo = jwtUtils.parseToken(token);

            request.setAttribute("token", token);
            request.setAttribute("jwtInfo", jwtInfo);

            configureSecurityContext(jwtInfo);
        } catch (Exception ex) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            PrintWriter responseWriter = response.getWriter();
            responseWriter.print("{\"error_code\": \"unauthorized\", \"message\": \"Invalid token\"}");
            return;
        }

        chain.doFilter(request, response);
    }

    private void configureSecurityContext(JwtInfo jwtInfo) {
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + jwtInfo.role());

        SecurityContextHolder.getContext().setAuthentication(
            new UsernamePasswordAuthenticationToken(jwtInfo.username(), null, Set.of(authority)));
    }
}
