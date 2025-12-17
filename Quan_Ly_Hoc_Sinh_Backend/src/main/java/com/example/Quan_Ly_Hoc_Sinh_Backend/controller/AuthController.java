package com.example.Quan_Ly_Hoc_Sinh_Backend.controller;



import com.example.Quan_Ly_Hoc_Sinh_Backend.dto.JwtResponse;
import com.example.Quan_Ly_Hoc_Sinh_Backend.dto.LoginRequest;
import com.example.Quan_Ly_Hoc_Sinh_Backend.model.Entity.Employee;
import com.example.Quan_Ly_Hoc_Sinh_Backend.service.JWT.JwtService;
import com.example.Quan_Ly_Hoc_Sinh_Backend.service.EmployeeDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private EmployeeDetailsService employeeDetailsService;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        try {
            // 1. Thực hiện xác thực bằng AuthenticationManager
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );

            // 2. Nếu thành công, tạo Token
            String jwtToken = jwtService.generateToken(loginRequest.getUsername());

            // 3. Lấy thông tin nhân viên để trả về cho Client (FE)
            Employee employee = employeeDetailsService.findByUsername(loginRequest.getUsername());

            // Lấy vai trò đầu tiên (thường là vai trò chính)
            String role = employee.getRoles().stream()
                    .map(r -> r.getName().name())
                    .findFirst()
                    .orElse("ROLE_USER");

            return ResponseEntity.ok(new JwtResponse(jwtToken, employee.getUsername(), role));

        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Sai tên đăng nhập hoặc mật khẩu!");
        }
    }
}
