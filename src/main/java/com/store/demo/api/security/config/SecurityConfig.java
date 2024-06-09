package com.store.demo.api.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.store.demo.api.security.entity.User;
import com.store.demo.api.security.repository.UserRepository;

import java.util.stream.Collectors;

@Configuration
@ComponentScan(basePackages = "com.store.demo.api.security")
// @EnableJpaRepositories(basePackages = {
//         "com.store.demo.api.security.repository"
// }, entityManagerFactoryRef = "H2EntityManagerFactory", transactionManagerRef = "H2TransactionManager")
@EntityScan(basePackages = "com.store.demo.api.security.entity")
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    LocalContainerEntityManagerFactoryBean entityManagerFactory;

    private UserRepository userRepository;

    public SecurityConfig(UserRepository userRepository) {
    this.userRepository = userRepository;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/swagger-ui/**").permitAll()
                        .requestMatchers("/h2-console/**").hasAnyRole("ADMIN")
                        .requestMatchers("/admin/**").hasAnyRole("ADMIN")
                        .requestMatchers("/api/products/**","/api/suppliers/**").hasAnyRole("ADMIN", "USER")
                        .requestMatchers("/products/test/**").hasAnyRole("ALL-TOP-PRIVILEGES")
                        .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults());

        http.cors(Customizer.withDefaults());
        // Configuracion para h2
        http
                .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.sameOrigin()))
                .csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**", "/products/**")); // Deshabilitar CSRF para H2
        return http.build();

    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        return username -> {
            User user = userRepository.findByUsername(username);
            if (user == null) {
                throw new UsernameNotFoundException("User not found");
            }
            return org.springframework.security.core.userdetails.User
                    .withUsername(user.getUsername())
                    .password(user.getPassword())
                    .authorities(user.getRoles()
                            .stream()
                            .map(role -> new SimpleGrantedAuthority(role.getName()))
                            .collect(Collectors.toList()))
                    .build();
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
