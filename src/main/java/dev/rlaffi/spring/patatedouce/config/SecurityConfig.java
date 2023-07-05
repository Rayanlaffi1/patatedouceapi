package dev.rlaffi.spring.patatedouce.config;

import dev.rlaffi.spring.patatedouce.security.JwtAuthConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig  {

    private final JwtAuthConverter jwtAuthConverter;

    public SecurityConfig(JwtAuthConverter jwtAuthConverter) {
        this.jwtAuthConverter = jwtAuthConverter;
    }

    @Bean
    SessionAuthenticationStrategy sessionAuthenticationStrategy() {
        return new RegisterSessionAuthenticationStrategy(new SessionRegistryImpl());
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/api/**", "/swagger-ui/**").permitAll()

                .requestMatchers(HttpMethod.PUT,"/utilisateur/creation/maraicher/**").permitAll()
                .requestMatchers(HttpMethod.PUT,"/utilisateur/creation/client/**").permitAll()
                .requestMatchers(HttpMethod.GET,"/utilisateur/**").hasRole("ADMIN")

                .requestMatchers(HttpMethod.PUT,"/aliment").hasAnyRole("ADMIN", "MARAICHER")
                .requestMatchers(HttpMethod.DELETE,"/aliment/**").hasAnyRole("ADMIN", "MARAICHER")
                .requestMatchers(HttpMethod.GET,"/aliment/**").permitAll()

                .anyRequest().authenticated();
        http.oauth2ResourceServer().jwt().jwtAuthenticationConverter(jwtAuthConverter);
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        return http.build();
    }

}
