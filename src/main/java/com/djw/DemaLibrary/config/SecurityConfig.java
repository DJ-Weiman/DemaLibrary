package com.djw.DemaLibrary.config;

import com.djw.DemaLibrary.domain.entities.UserEntity;
import com.djw.DemaLibrary.repositories.UserRepository;
import com.djw.DemaLibrary.security.JwtAuthenticationFilter;
import com.djw.DemaLibrary.security.LibraryUserDetailsService;
import com.djw.DemaLibrary.services.AuthenticationService;
import org.apache.catalina.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.resource.web.BearerTokenAuthenticationEntryPoint;
import org.springframework.security.oauth2.server.resource.web.access.BearerTokenAccessDeniedHandler;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.time.LocalDateTime;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository){
        LibraryUserDetailsService libraryUserDetailsService =  new LibraryUserDetailsService(userRepository);

        String username = "djw";
        userRepository.findByName(username).orElseGet(() -> {
            UserEntity user = UserEntity.builder()
                    .name(username)
                    .email("test@123.com")
                    .userPassword(passwordEncoder().encode("password"))
                    .created_at(LocalDateTime.now())
                    .build();
            return userRepository.save(user);
        });

        return libraryUserDetailsService;
    }

//    @Bean
//    public JwtAuthenticationFilter jwtAuthenticationFilter(AuthenticationService authenticationService){
//        return new JwtAuthenticationFilter(authenticationService);
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http) throws Exception{
       return http
                .authorizeHttpRequests(auth -> auth
                    .requestMatchers(HttpMethod.POST, "/library/auth/login").permitAll()
                    .requestMatchers(HttpMethod.POST, "/library/auth/register").permitAll()
                    .requestMatchers(HttpMethod.GET, "/library/books/**").permitAll()
                    .requestMatchers(HttpMethod.POST, "/library/books/**").permitAll()
                )
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
    //            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
                .oauth2ResourceServer(server -> server
                        .jwt(Customizer.withDefaults())
                        .authenticationEntryPoint(
                                new BearerTokenAuthenticationEntryPoint())
                        .accessDeniedHandler(
                                new BearerTokenAccessDeniedHandler())
                ).build();
    }

}
