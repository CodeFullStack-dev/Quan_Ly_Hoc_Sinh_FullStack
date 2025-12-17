package com.example.Quan_Ly_Hoc_Sinh_Backend.service;

import com.example.Quan_Ly_Hoc_Sinh_Backend.model.Entity.Employee;
import com.example.Quan_Ly_Hoc_Sinh_Backend.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class EmployeeDetailsService implements UserDetailsService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee findByUsername(String username) {
        return employeeRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Không tìm thấy người dùng với username: " + username));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee employee = findByUsername(username);

        // Map ERole.name() sang String Authority
        return new org.springframework.security.core.userdetails.User(
                employee.getUsername(),
                employee.getPassword(),
                mapRolesToAuthorities(employee)
        );
    }

    // Ánh xạ các vai trò sang quyền hạn
    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Employee employee) {
        return employee.getRoles().stream()
                // Lưu ý: Spring Security tự thêm tiền tố "ROLE_" nếu bạn dùng .hasRole()
                // Nhưng khi dùng .hasAuthority(), bạn phải dùng tên đầy đủ từ Enum (ROLE_ADMIN, ROLE_STAFF,...)
                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList());
    }
}
