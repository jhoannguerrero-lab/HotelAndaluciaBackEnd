package com.hotelandalucia.backend.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;

    public SecurityConfig(JwtAuthFilter jwtAuthFilter) {
        this.jwtAuthFilter = jwtAuthFilter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Conecta el CorsConfigurationSource definido en WebConfig con
                // la cadena de Spring Security -- sin esto, el preflight OPTIONS
                // se bloquea con 403 antes de llegar a esa configuracion.
                .cors(Customizer.withDefaults())
                // API stateless (sin sesiones de servidor): CSRF no aplica aqui.
                .csrf(csrf -> csrf.disable())
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                .authorizeHttpRequests(auth -> auth
                        // El preflight OPTIONS del navegador nunca trae el header
                        // Authorization: debe quedar siempre permitido.
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                        // Login es publico, todo lo demas necesita token.
                        .requestMatchers("/api/auth/**").permitAll()


                        // Lectura (GET): cualquier usuario logueado, sea CONSULTA o GESTOR.
                        .requestMatchers(HttpMethod.GET, "/api/habitaciones/**", "/api/reservas/**")
                        .authenticated()

                        // Escritura (POST/PUT/DELETE): solo el rol GESTOR.
                        .requestMatchers(HttpMethod.POST, "/api/habitaciones/**", "/api/reservas/**")
                        .hasRole("GESTOR")
                        .requestMatchers(HttpMethod.PUT, "/api/habitaciones/**", "/api/reservas/**")
                        .hasRole("GESTOR")
                        .requestMatchers(HttpMethod.DELETE, "/api/habitaciones/**", "/api/reservas/**")
                        .hasRole("GESTOR")

                        .anyRequest().authenticated()
                )

                // Nuestro filtro corre antes que el filtro de login por
                // usuario/contrasena que Spring trae por defecto.
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
