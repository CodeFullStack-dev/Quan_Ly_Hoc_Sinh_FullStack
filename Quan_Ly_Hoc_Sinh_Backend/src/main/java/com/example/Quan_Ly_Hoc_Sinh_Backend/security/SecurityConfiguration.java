package com.example.Quan_Ly_Hoc_Sinh_Backend.security;

import com.example.Quan_Ly_Hoc_Sinh_Backend.service.EmployeeDetailsService;
import com.example.Quan_Ly_Hoc_Sinh_Backend.service.JWT.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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

import static com.example.Quan_Ly_Hoc_Sinh_Backend.model.Enum.Erole.*;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

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
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        // SỬA LỖI Ở ĐÂY: Dùng constructor có 1 tham số
        DaoAuthenticationProvider dap = new DaoAuthenticationProvider(employeeDetailsService);

        // Chỉ cần set PasswordEncoder
        dap.setPasswordEncoder(passwordEncoder());
        return dap;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(AbstractHttpConfigurer::disable)

                .authenticationProvider(authenticationProvider())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                .authorizeHttpRequests(config -> config
                        // Công khai
                        .requestMatchers("/auth/**").permitAll()

                        // ADMIN
                        .requestMatchers("/schools/**", "/employees/**").hasAnyAuthority("ROLE_ADMIN", "ADMIN")

                        // ADMIN & STAFF
                        .requestMatchers("/classes/**", "/subjects/**").hasAnyAuthority(ROLE_ADMIN.name(), ROLE_STAFF.name())

                        // TEACHER
                        .requestMatchers("/scores/**", "/lesson-logs/**").hasAuthority(ROLE_TEACHER.name())

                        // Yêu cầu xác thực cho tất cả request còn lại
                        .anyRequest().authenticated()
                );

        // Thêm JWT Filter
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        // Thay "*" bằng địa chỉ cụ thể của Frontend (ví dụ http://localhost:3000)
        // Hoặc dùng setAllowedOriginPatterns nếu muốn linh hoạt hơn
        config.setAllowedOriginPatterns(List.of("*"));
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
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