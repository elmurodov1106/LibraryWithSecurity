package uz.g22.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import uz.g22.service.AuthService;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
  private final AuthService authService;

private final PasswordEncoder passwordEncoder;

  @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

     return http
              .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
              .and()
              .authorizeHttpRequests()
              .anyRequest()
              .permitAll()
              .and()
             .csrf().disable()
              .build();

//    return http
//            .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
//            .and()
//            .authorizeHttpRequests()
//            .requestMatchers("/api/v1/auth/librarian/sign-up").permitAll()
//            .requestMatchers(
//                    "/api/v1/book/get-all",
//                    "/api/v1/book/search"
//            ).authenticated()
//            .requestMatchers(
//                    "/api/v1/book/add",
//                    "/api/v1/book/{bookId}/update",
//                    "/api/v1/book/{bookId}/delete"
//            ).hasRole("LIBRARIAN")
//            .requestMatchers("/api/v1/user/**").hasRole("ADMIN")
//            .anyRequest().authenticated()
//            .and()
//            .csrf().disable()
//            .build();

  }

  @Bean
  public AuthenticationManager authManager(HttpSecurity httpSecurity) throws Exception {
    AuthenticationManagerBuilder authenticationManagerBuilder
            = httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
    authenticationManagerBuilder.userDetailsService(authService)
            .passwordEncoder(passwordEncoder);
    return authenticationManagerBuilder.build();
  }



}
