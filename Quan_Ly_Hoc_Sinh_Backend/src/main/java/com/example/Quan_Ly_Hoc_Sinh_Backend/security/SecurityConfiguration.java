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

import static com.example.Quan_Ly_Hoc_Sinh_Backend.model.Enum.ERole.*;


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

                .authorizeHttpRequests(config -> config
                        // Chỉ API login và các API public khác (nếu có) là không cần token
                        .requestMatchers("/auth/login").permitAll()

                        // API /me PHẢI yêu cầu xác thực để JwtFilter trích xuất thông tin người dùng
                        .requestMatchers("/auth/me").authenticated()

                        // ADMIN: Quản lý hạ tầng và nhân sự
                        .requestMatchers("/schools/**", "/employees/**").hasAnyAuthority("ROLE_ADMIN")

                        // ADMIN & STAFF: Quản lý vận hành lớp học
                        .requestMatchers("/classes/**", "/subjects/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_STAFF")

                        // TEACHER, STAFF, ADMIN: Quản lý chuyên môn
                        .requestMatchers("/scores/**", "/lesson-logs/**", "/grade-books/**").hasAnyAuthority("ROLE_TEACHER", "ROLE_STAFF", "ROLE_ADMIN")

                        // Tất cả các yêu cầu khác đều phải đăng nhập
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