package com.example.Quan_Ly_Hoc_Sinh_Backend.security;

import com.example.Quan_Ly_Hoc_Sinh_Backend.service.EmployeeDetailsService;
import com.example.Quan_Ly_Hoc_Sinh_Backend.service.JWT.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

import static com.example.Quan_Ly_Hoc_Sinh_Backend.model.Enum.ERole.*;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Value("${frontend_url:http://localhost:5173/}")
    private String frontend_url;

    @Autowired
    private JwtFilter jwtFilter;

    @Autowired
    private EmployeeDetailsService employeeDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * KHẮC PHỤC LỖI CONSTRUCTOR: Truyền EmployeeDetailsService vào constructor
     */
    public DaoAuthenticationProvider authenticationProvider() {
        // Truyền trực tiếp service vào constructor
        DaoAuthenticationProvider dap = new DaoAuthenticationProvider(employeeDetailsService);

        dap.setPasswordEncoder(passwordEncoder());
        return dap;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider())
                .authorizeHttpRequests(config -> config
                        // Sử dụng mảng từ file Endpoints
                        .requestMatchers(HttpMethod.POST, Endpoints.PUBLIC_POST).permitAll()
                        .requestMatchers(HttpMethod.GET, Endpoints.PUBLIC_GET).permitAll()

                        // Yêu cầu xác thực cụ thể cho /me
                        .requestMatchers("/auth/me").authenticated()

                        // Phân quyền dựa trên mảng Endpoint
                        .requestMatchers(Endpoints.ADMIN_ENDPOINT).hasAuthority("ROLE_ADMIN")
                        .requestMatchers(Endpoints.SHARED_MANAGEMENT).hasAnyAuthority("ROLE_ADMIN", "ROLE_STAFF", "ROLE_TEACHER")

                        .anyRequest().authenticated()
                );

        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        // Sử dụng biến frontend_url đã inject thay vì dùng "*"
        config.setAllowedOrigins(List.of(frontend_url));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}