package com.example.Quan_Ly_Hoc_Sinh_Backend.service.Parent;

import com.example.Quan_Ly_Hoc_Sinh_Backend.dto.ParentDTOs.ParentRequest;
import com.example.Quan_Ly_Hoc_Sinh_Backend.dto.ParentDTOs.ParentResponse;
import com.example.Quan_Ly_Hoc_Sinh_Backend.mapper.ParentMapper;
import com.example.Quan_Ly_Hoc_Sinh_Backend.model.Entity.Parent;
import com.example.Quan_Ly_Hoc_Sinh_Backend.model.Entity.Student;
import com.example.Quan_Ly_Hoc_Sinh_Backend.model.Entity.StudentParent;
import com.example.Quan_Ly_Hoc_Sinh_Backend.model.Enum.ERelationshipType;
import com.example.Quan_Ly_Hoc_Sinh_Backend.repository.ParentRepository;
import com.example.Quan_Ly_Hoc_Sinh_Backend.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ParentServiceImpl implements ParentService {

    @Autowired
    private ParentRepository parentRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ParentMapper parentMapper;

    @Override
    public ParentResponse addParentToStudent(ParentRequest request) {
        // 1. Tìm hoặc tạo mới Phụ huynh
        Parent parent = parentRepository.findByPhoneNumber(request.getPhoneNumber())
                .orElseGet(() -> {
                    Parent newParent = parentMapper.toEntity(request);
                    newParent.setOccupation(request.getJob());
                    // Đảm bảo Entity Parent đã có trường address
                    newParent.setAddress(request.getAddress());
                    return parentRepository.save(newParent);
                });

        // 2. FIX LỖI TÌM HỌC SINH (Convert String -> Long)
        Long studentId = Long.parseLong(request.getStudentId());
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy học sinh ID: " + studentId));

        // 3. FIX LỖI ENUM
        StudentParent link = new StudentParent();
        link.setStudent(student);
        link.setParent(parent);
        link.setRelationshipType(ERelationshipType.valueOf(request.getRelationship().toUpperCase()));

        // 4. Lưu liên kết
        parent.getChildrenLinks().add(link);
        parentRepository.save(parent);

        // 5. Trả về Response
        ParentResponse response = parentMapper.toResponse(parent);
        response.setRelationship(request.getRelationship());
        response.setStudentName(student.getFullName());

        return response;
    }

    @Override
    public ParentResponse updateParent(Long id, ParentRequest request) {
        Parent parent = parentRepository.findById(id)
                .orElseThrow(() ->  new RuntimeException("Phụ huynh không tồn tại"));

        parent.setFullName(request.getFullName());
        parent.setOccupation(request.getJob());
        parent.setEmail(request.getEmail());
        parent.setAddress(request.getAddress());

        return parentMapper.toResponse(parentRepository.save(parent));
    }

    @Override
    public List<ParentResponse> getAllParentsInfo(String query) { // Sửa kiểu trả về ở đây
        List<Parent> parents;

        if (query == null || query.trim().isEmpty()) {
            parents = parentRepository.findAll();
        } else {
            parents = parentRepository.getAllParentsInfo(query.trim());
        }

        // Trả về List<ParentResponse> là hoàn toàn chính xác với logic map này
        return parents.stream()
                .map(parentMapper::toResponse)
                .collect(Collectors.toList());
    }

}
