package com.lawoffice.managementapi.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    private CustomUserDetailService userDetailService;
    public SecurityConfig(CustomUserDetailService userDetailService) {
        this.userDetailService = userDetailService;
    }



    /* 1. Primero
    * Este fragmento de código configura un filtro de seguridad
    * para todas las solicitudes HTTP,
    * deshabilitando la protección CSRF y requiriendo autenticación básica para todas las solicitudes.
    * */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(http -> {
                    http.requestMatchers("/api/auth/**").permitAll();
                    http.anyRequest().authenticated();
                })
                .httpBasic(Customizer.withDefaults())
                .build();

    }

    /* 2. Segundo si no tienes datos en la BD
    * Este código configura un servicio de detalles de usuario en memoria
    * con dos usuarios predefinidos: uno con rol de administrador y otro con rol de usuario.
    * */
    @Bean
    public UserDetailsService user() {
        UserDetails admin = User.builder()
                .username("admin")
                .password("12345")
                .roles("ADMIN")
                .build();
        UserDetails user = User.builder()
                .username("user")
                .password("12345")
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(admin, user);

    }

    /*
    * Este código configura y devuelve un administrador de autenticación
    * utilizando la configuración de autenticación proporcionada.
    * */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /*
    * Este fragmento de código configura y devuelve un
    * codificador de contraseñas utilizando el algoritmo BCrypt.
    * */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
