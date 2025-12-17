package com.example.Quan_Ly_Hoc_Sinh_Backend.service.Employee;

import com.example.Quan_Ly_Hoc_Sinh_Backend.dto.EmployeeDTOs.EmployeeRequest;
import com.example.Quan_Ly_Hoc_Sinh_Backend.dto.EmployeeDTOs.EmployeeResponse;
import com.example.Quan_Ly_Hoc_Sinh_Backend.mapper.EmployeeMapper;
import com.example.Quan_Ly_Hoc_Sinh_Backend.model.Entity.Employee;
import com.example.Quan_Ly_Hoc_Sinh_Backend.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public EmployeeResponse register(EmployeeRequest request) {
        Employee employee = employeeMapper.toEntity(request);

        // PHẢI CÓ DÒNG NÀY: Mã hóa mật khẩu từ request
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        employee.setPassword(encodedPassword);

        // Gán trường học và các thông tin khác...
        // employee.setSchool(...);

        return employeeMapper.toResponse(employeeRepository.save(employee));
    }
}
