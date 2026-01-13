package com.example.Quan_Ly_Hoc_Sinh_Backend.security;

import org.springframework.http.HttpMethod;

public class Endpoints {
    // Các đường dẫn GET công khai
    public static final String[] PUBLIC_GET = {
            "/auth/me" // /me cần auth nhưng để permitAll ở đây để custom filter xử lý sau hoặc kiểm soát qua .authenticated()
    };

    // Các đường dẫn POST công khai
    public static final String[] PUBLIC_POST = {
            "/auth/login"
    };

    // Quyền ADMIN tuyệt đối
    public static final String[] ADMIN_ENDPOINT = {
            "/schools/**",
            "/employees/**"
    };

    // Quyền hỗn hợp (ADMIN, STAFF, TEACHER)
    public static final String[] SHARED_MANAGEMENT = {
            "/classes/**",
            "/subjects/**",
            "/scores/**",
            "/lesson-logs/**",
            "/grade-books/**",
            "/parents/**",
            "/academic-records/**"
    };
}
