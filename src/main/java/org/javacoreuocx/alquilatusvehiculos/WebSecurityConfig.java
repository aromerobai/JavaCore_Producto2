package org.javacoreuocx.alquilatusvehiculos;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    /* @Autowired
    private PasswordEncoder passwordEncoder;*/

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers(
                                "/",
                                "/register",
                                "/static/**",
                                "/images/**",
                                "/css/**",
                                "/js/**",
                                "/home",
                                "/coches",
                                "/reservas/**",
                                "/contacto",
                                "/administracion/**"
                        ).permitAll()
                        //.requestMatchers("/home", "/coches", "/reservas", "/contacto").hasRole("USER")
                        //.requestMatchers("/administracion/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/home")
                        //.loginPage("/login")
                        .permitAll()
                )
                .logout((logout) -> logout.permitAll()
                        //.logoutUrl("/login")
                );

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder()
            .username("user")
            .password("password")
            //.password(passwordEncoder.encode("password"))
            .roles("USER")
            .build();

        UserDetails admin = User.withDefaultPasswordEncoder()
            .username("admin")
            //.password(passwordEncoder.encode("password"))
            .password("password")
            .roles("ADMIN")
            .build();

        return new InMemoryUserDetailsManager(user, admin);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}