package com.example.Quan_Ly_Hoc_Sinh_Backend.service.Subject;

import com.example.Quan_Ly_Hoc_Sinh_Backend.dto.SubjectDTOs.SubjectRequest;
import com.example.Quan_Ly_Hoc_Sinh_Backend.dto.SubjectDTOs.SubjectResponse;
import com.example.Quan_Ly_Hoc_Sinh_Backend.mapper.SubjectMapper;
import com.example.Quan_Ly_Hoc_Sinh_Backend.model.Entity.Subject;
import com.example.Quan_Ly_Hoc_Sinh_Backend.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubjectServiceImpl implements SubjectService {
    @Autowired
    private  SubjectRepository subjectRepository;

    @Autowired
    private  SubjectMapper subjectMapper;

    @Override
    @Transactional
    public SubjectResponse createSubject(SubjectRequest request) {
        // Kiểm tra trùng mã môn học dựa trên Repository đã có
        if (subjectRepository.findBySubjectCode(request.getSubjectCode()).isPresent()) {
            throw new RuntimeException("Mã môn học đã tồn tại: " + request.getSubjectCode());
        }
        Subject subject = subjectMapper.toEntity(request);
        return subjectMapper.toResponse(subjectRepository.save(subject));
    }

    @Override
    public List<SubjectResponse> getAllSubjects() {
        return subjectRepository.findAll().stream()
                .map(subjectMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public SubjectResponse updateSubject(Long id, SubjectRequest request) {
        Subject subject = subjectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy môn học ID: " + id));

        subject.setSubjectName(request.getSubjectName());
        subject.setSubjectCode(request.getSubjectCode());

        return subjectMapper.toResponse(subjectRepository.save(subject));
    }

    @Override
    public SubjectResponse getSubjectByCode(String code) {
        // Triển khai hàm này để hết lỗi ở image_8e1c95.png
        return subjectRepository.findBySubjectCode(code)
                .map(subjectMapper::toResponse)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy môn học có mã: " + code));
    }

    @Override
    @Transactional
    public void deleteSubject(Long id) {
        if (!subjectRepository.existsById(id)) {
            throw new RuntimeException("Không tìm thấy môn học để xóa");
        }
        subjectRepository.deleteById(id);
    }


}
