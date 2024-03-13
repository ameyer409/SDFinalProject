package com.skilldistillery.enchantedrealm.security;
import static org.springframework.security.config.Customizer.withDefaults;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    // this you get for free when you configure the db connection in application.properties file
    @Autowired
    private DataSource dataSource; // From javax.sql.DataSource

    // this bean is created in the application starter class if you're looking for it
    @Autowired
    private PasswordEncoder encoder;
	
    @Bean 
    SecurityFilterChain createFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable());
        http.httpBasic(withDefaults()); // Use HTTP Basic Authentication. Other Authentication: OAth, Bear (JWT)
        http.authorizeHttpRequests(
          authorize -> authorize
            .requestMatchers(HttpMethod.OPTIONS, "/api/**").permitAll() // For CORS, the preflight request to see which req method is allowed for each route 
            .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()     // will hit the OPTIONS on the route
            .requestMatchers(HttpMethod.GET, "/api/companies/{id}").permitAll()     // will hit the OPTIONS on the route
            .requestMatchers(HttpMethod.GET, "/api/companies/{id}/jobpostings").permitAll()     // will hit the OPTIONS on the route
            .requestMatchers(HttpMethod.GET, "/api/jobpostings").permitAll()     // will hit the OPTIONS on the route
            .requestMatchers(HttpMethod.GET, "/api/jobpostings/{id}").permitAll()     // will hit the OPTIONS on the route
            .requestMatchers("/api/**").authenticated() // Requests for our REST API must be authorized.
            .anyRequest().permitAll());                 // All other requests are allowed without authentication.
        // Order matters
        http.sessionManagement(management -> management
                              .sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }
    
    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        // Check if username/password are valid, and user currently allowed to authenticate
        String userQuery = "SELECT username, password, enabled FROM user WHERE username=?";
        // Check what authorities the user has
        String authQuery = "SELECT username, role FROM user WHERE username=?";
        auth
        .jdbcAuthentication()
        .dataSource(dataSource)
        .usersByUsernameQuery(userQuery)
        .authoritiesByUsernameQuery(authQuery)
        .passwordEncoder(encoder);
    }
    
}