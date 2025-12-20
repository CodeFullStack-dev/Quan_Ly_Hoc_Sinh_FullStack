package com.example.Quan_Ly_Hoc_Sinh_Backend.service.Subject;

import com.example.Quan_Ly_Hoc_Sinh_Backend.dto.SubjectDTOs.SubjectRequest;
import com.example.Quan_Ly_Hoc_Sinh_Backend.dto.SubjectDTOs.SubjectResponse;

import java.util.List;

public interface SubjectService {
    SubjectResponse createSubject(SubjectRequest request);
    List<SubjectResponse> getAllSubjects();
    // Đảm bảo có Long id ở đây để khớp với Impl
    SubjectResponse updateSubject(Long id, SubjectRequest request);
    // Phương thức đang báo lỗi đỏ vì thiếu Impl
    SubjectResponse getSubjectByCode(String code);
    void deleteSubject(Long id);
}
