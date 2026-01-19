package com.example.Quan_Ly_Hoc_Sinh_Backend.service.AcademicRecord;

import com.example.Quan_Ly_Hoc_Sinh_Backend.dto.AcademicRecordDTOs.AcademicRecordRequest;
import com.example.Quan_Ly_Hoc_Sinh_Backend.dto.AcademicRecordDTOs.AcademicRecordResponse;

import java.util.List;

public interface AcademicRecordService {
    // --- Nhóm chức năng Tạo & Cập nhật (Sync/Update) ---
    AcademicRecordResponse createAcademicRecord(AcademicRecordRequest request);

    AcademicRecordResponse updateAcademicRecord(AcademicRecordRequest request);

    AcademicRecordResponse syncGrades(AcademicRecordRequest request);

    // --- Nhóm chức năng Tìm kiếm (Search/Read) ---
    List<AcademicRecordResponse> getStudentHistory(String studentCode);
    AcademicRecordResponse getRecordByStudentCodeAndYear(String studentCode, String schoolYear);

    // Xóa theo mã học sinh và năm học (vì 1 mã học sinh có nhiều học bạ các năm)
    void deleteRecordByCode(String studentCode, String schoolYear);
}
