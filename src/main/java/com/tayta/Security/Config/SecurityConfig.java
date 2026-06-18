package com.tayta.Security.Config;

import com.tayta.Security.Jwt.JwtAuthenticationEntryPoint;
import com.tayta.Security.Jwt.JwtAuthenticationFilter;
import com.tayta.Security.Services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
            .csrf(csrf -> csrf.disable())
            .exceptionHandling(ex -> ex.authenticationEntryPoint(unauthorizedHandler))
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                // ── Público ──────────────────────────────────────────
                .requestMatchers("/api/auth/**").permitAll()
                .requestMatchers("/swagger-ui/**", "/api-docs/**", "/swagger-ui.html", "/v3/api-docs/**").permitAll()

                // ── Solo ADMIN ───────────────────────────────────────
                .requestMatchers("/api/queries/**").hasRole("ADMIN")
                .requestMatchers("/api/users/**").hasRole("ADMIN")

                // ── ADMIN + GUARDIAN ─────────────────────────────────
                .requestMatchers("/api/subscriptions/**", "/api/payments/**").hasAnyRole("ADMIN", "GUARDIAN")

                // ── ADMIN + NURSE ────────────────────────────────────
                .requestMatchers("/api/nurse-calendars/**").hasAnyRole("ADMIN", "NURSE")

                // ── Catálogos y perfiles: leen todos, escribe ADMIN ──
                .requestMatchers(HttpMethod.GET,
                        "/api/roles/**", "/api/health-centers/**", "/api/locations/**",
                        "/api/guardians/**", "/api/nurses/**", "/api/guardian-elderly/**").authenticated()
                .requestMatchers(
                        "/api/roles/**", "/api/health-centers/**", "/api/locations/**",
                        "/api/guardians/**", "/api/nurses/**", "/api/guardian-elderly/**").hasRole("ADMIN")

                // ── Elderly: leen todos · crean/editan ADMIN+GUARDIAN · borra ADMIN ──
                .requestMatchers(HttpMethod.GET, "/api/elderly/**").authenticated()
                .requestMatchers(HttpMethod.DELETE, "/api/elderly/**").hasRole("ADMIN")
                .requestMatchers("/api/elderly/**").hasAnyRole("ADMIN", "GUARDIAN")

                // ── Monitoreo / historia clínica / calendario: leen todos · escribe NURSE+ADMIN ──
                .requestMatchers(HttpMethod.GET,
                        "/api/monitoring/**", "/api/clinical-records/**", "/api/calendars/**").authenticated()
                .requestMatchers(
                        "/api/monitoring/**", "/api/clinical-records/**", "/api/calendars/**").hasAnyRole("ADMIN", "NURSE")

                .anyRequest().authenticated()
            );

        http.authenticationProvider(authenticationProvider());
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("http://localhost:4200"));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setExposedHeaders(List.of("Authorization"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
