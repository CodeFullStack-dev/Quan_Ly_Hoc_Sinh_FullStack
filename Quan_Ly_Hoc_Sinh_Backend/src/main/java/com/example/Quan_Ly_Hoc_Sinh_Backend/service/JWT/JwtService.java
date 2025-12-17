package com.example.Quan_Ly_Hoc_Sinh_Backend.service.JWT;


import com.example.Quan_Ly_Hoc_Sinh_Backend.model.Entity.Employee;
import com.example.Quan_Ly_Hoc_Sinh_Backend.model.Entity.Role;
import com.example.Quan_Ly_Hoc_Sinh_Backend.service.EmployeeDetailsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;

@Component
public class JwtService {

    // SỬ DỤNG KEY CỨNG HOẶC @Value nếu bạn đã cấu hình trong application.properties
    private static final String KEY_SECRET = "MTIzNDU2NDU5OThEMzIxM0F6eGMzNTE2NTQzMjEzMjE2NTQ5OHEzMTNhMnMxZDMyMnp4M2MyMQ==";

    @Autowired
    private EmployeeDetailsService employeeDetailsService;

    private static final long EXPIRATION_TIME = 30 * 60 * 1000;

    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        Employee employee = employeeDetailsService.findByUsername(username);

        Set<Role> roles = employee.getRoles();
        if (roles != null && !roles.isEmpty()) {
            String highestRole = roles.stream()
                    .map(role -> role.getName().name())
                    .sorted()
                    .findFirst()
                    .orElse("ROLE_STUDENT");

            claims.put("role", highestRole);
        }

        return createToken(claims, username);
    }

    private String createToken(Map<String, Object> claims, String username) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME) )
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getSigningKey() {
        byte[] keyByte = Decoders.BASE64.decode(KEY_SECRET);
        return Keys.hmacShaKeyFor(keyByte);
    }

    // Các phương thức Extract và Validate JWT
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }
}
