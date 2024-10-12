package org.example.noteCollector_V2.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Value("${security.username}")
    private String username;

    @Value("${security.password}")
    private String password;

    @Value("${security.role}")
    private String role;

    // configure security
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
       http.csrf(AbstractHttpConfigurer::disable)
               .authorizeHttpRequests()
               .anyRequest().authenticated()
               .and()
               .httpBasic();
        return http.build();

    }
    // configure user
    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager(){
        UserDetails principleUser = User.withDefaultPasswordEncoder()
                .username(username)
                .password(password)
                .roles(role).build();
        return new InMemoryUserDetailsManager(principleUser);

    }
}
