package com.example.Quan_Ly_Hoc_Sinh_Backend.service.SchoolClass;

import com.example.Quan_Ly_Hoc_Sinh_Backend.dto.SchoolClassDTOs.SchoolClassRequest;
import com.example.Quan_Ly_Hoc_Sinh_Backend.dto.SchoolClassDTOs.SchoolClassResponse;
import com.example.Quan_Ly_Hoc_Sinh_Backend.mapper.SchoolClassMapper;
import com.example.Quan_Ly_Hoc_Sinh_Backend.model.Entity.Employee;
import com.example.Quan_Ly_Hoc_Sinh_Backend.model.Entity.School;
import com.example.Quan_Ly_Hoc_Sinh_Backend.model.Entity.SchoolClass;
import com.example.Quan_Ly_Hoc_Sinh_Backend.repository.EmployeeRepository;
import com.example.Quan_Ly_Hoc_Sinh_Backend.repository.SchoolClassRepository;
import com.example.Quan_Ly_Hoc_Sinh_Backend.repository.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SchoolClassServiceImpl implements SchoolClassService {

    @Autowired
    private SchoolClassRepository schoolClassRepository;

    @Autowired
    private SchoolRepository schoolRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private SchoolClassMapper schoolClassMapper; // Tiêm mapper mới tạo

    @Override
    @Transactional // Nên có transactional khi ghi dữ liệu
    public SchoolClassResponse createClass(SchoolClassRequest request) {
        if(schoolClassRepository.existsByHomeroomTeacherId(request.getTeacherId())) {
            throw new RuntimeException("Giáo viên này đã chủ nhiệm 1 lớp khác");
        }

        School school = schoolRepository.findById(request.getSchoolId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy trường học"));
        Employee teacher = employeeRepository.findById(request.getTeacherId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy giáo viên"));

        // Sử dụng Mapper
        SchoolClass schoolClass = schoolClassMapper.toEntity(request);
        schoolClass.setSchool(school);
        schoolClass.setHomeroomTeacher(teacher);

        SchoolClass savedClass = schoolClassRepository.save(schoolClass);

        return schoolClassMapper.toResponse(savedClass);
    }

    @Override
    public List<SchoolClassResponse> getAllClasses() {
        return schoolClassRepository.findAll().stream()
                .map(schoolClassMapper::toResponse) // Dùng Method Reference của Mapper
                .collect(Collectors.toList());
    }

    // Phương thức mapToResponse cũ có thể xóa bỏ
}
