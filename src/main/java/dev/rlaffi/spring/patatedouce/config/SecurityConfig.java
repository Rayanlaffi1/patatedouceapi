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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

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
        http
                .cors()
                .configurationSource(corsConfigurationSource())
                .and()
                .csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/api/**", "/swagger-ui/**").permitAll()

                .requestMatchers(HttpMethod.PUT,"/utilisateur/creation/maraicher/**").permitAll()
                .requestMatchers(HttpMethod.PUT,"/utilisateur/creation/client/**").permitAll()
                .requestMatchers(HttpMethod.GET,"/utilisateur/**").hasRole("ADMIN")

                .requestMatchers(HttpMethod.PUT,"/aliment").hasAnyRole("ADMIN", "MARAICHER")
                .requestMatchers(HttpMethod.DELETE,"/aliment/**").hasAnyRole("ADMIN", "MARAICHER")
                .requestMatchers(HttpMethod.GET,"/aliment/**").permitAll()

                .requestMatchers(HttpMethod.GET,"/recette/**").permitAll()
                .requestMatchers(HttpMethod.PUT,"/recette/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST,"/recette/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE,"/recette/**").hasRole("ADMIN")

                .requestMatchers(HttpMethod.GET,"/typealiment/**").permitAll()
                .requestMatchers(HttpMethod.PUT,"/typealiment/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST,"/typealiment/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE,"/typealiment/**").hasRole("ADMIN")

                .requestMatchers(HttpMethod.GET,"/panier/**").permitAll()
                .requestMatchers(HttpMethod.PUT,"/panier/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST,"/panier/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE,"/panier/**").hasRole("ADMIN")

                .requestMatchers(HttpMethod.GET,"/etape/**").permitAll()
                .requestMatchers(HttpMethod.PUT,"/etape/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST,"/etape/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE,"/etape/**").hasRole("ADMIN")

                .requestMatchers(HttpMethod.GET,"/client/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET,"/maraicher/**").hasRole("ADMIN")

                .requestMatchers(HttpMethod.POST,"/client/**").hasAnyRole("ADMIN", "CLIENT")
                .requestMatchers(HttpMethod.POST,"/maraicher/**").hasAnyRole("ADMIN", "MARAICHER")

                .anyRequest().hasRole("ADMIN");
        http.oauth2ResourceServer().jwt().jwtAuthenticationConverter(jwtAuthConverter);
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        return http.build();
    }
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:8080","http://localhost"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
