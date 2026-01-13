package com.example.Quan_Ly_Hoc_Sinh_Backend.service.AcademicRecord;

import com.example.Quan_Ly_Hoc_Sinh_Backend.dto.AcademicRecordDTOs.AcademicRecordRequest;
import com.example.Quan_Ly_Hoc_Sinh_Backend.dto.AcademicRecordDTOs.AcademicRecordResponse;

import java.util.List;

public interface AcademicRecordService {
    // --- Nhóm chức năng Tạo & Cập nhật (Sync/Update) ---
    AcademicRecordResponse generateOrUpdateRecord(AcademicRecordRequest request);

    // --- Nhóm chức năng Tìm kiếm (Search/Read) ---
    List<AcademicRecordResponse> getStudentHistory(Long studentId);
    AcademicRecordResponse getRecordByStudentAndYear(Long studentId, String schoolYear);

    // --- Nhóm chức năng Xóa (Delete) ---
    void deleteRecord(Long id);
}
