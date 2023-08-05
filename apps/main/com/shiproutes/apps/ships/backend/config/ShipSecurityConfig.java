package com.shiproutes.apps.ships.backend.config;

import com.shiproutes.shared.infrastructure.auth.AuthorizationFilter;
import com.shiproutes.shared.infrastructure.auth.JwtUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class ShipSecurityConfig {

    private final JwtUtils jwtUtils;

    public ShipSecurityConfig(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration config = new CorsConfiguration();
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");

        source.registerCorsConfiguration("/**", config);

        return source;
    }

    @Profile("test")
    @EnableWebSecurity
    public static class SecurityDisabledConfig extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.cors().and().csrf().disable().authorizeRequests().antMatchers("/**").permitAll().anyRequest().authenticated();
        }

    }

    @Profile("!test")
    @EnableWebSecurity
    @EnableGlobalMethodSecurity(prePostEnabled = true)
    @DependsOn("corsConfigurationSource")
    public class SecurityEnabledConfig extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {

            // We are using token based authentication, csrf is not required
            http.cors().and().csrf().disable();

            // In a stateless server, no session is required
            http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

            // Filter to get application roles from the token
            http.addFilterBefore(new AuthorizationFilter(jwtUtils), UsernamePasswordAuthenticationFilter.class);
        }
    }

}
