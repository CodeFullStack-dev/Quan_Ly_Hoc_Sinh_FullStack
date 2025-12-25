package com.example.Quan_Ly_Hoc_Sinh_Backend.service.GradeBook;

import com.example.Quan_Ly_Hoc_Sinh_Backend.dto.GradeBookDTOs.GradeBookRequest;
import com.example.Quan_Ly_Hoc_Sinh_Backend.dto.GradeBookDTOs.GradeBookResponse;
import com.example.Quan_Ly_Hoc_Sinh_Backend.model.Enum.ESemester;

import java.util.List;

public interface GradeBookService {
    //Luồng tự động
    void calculateAndSaveAverage(Long studentId, Long subjectId, ESemester semester, String schoolYear);

    // Luồng nhập tay
    GradeBookResponse saveManual(GradeBookRequest request);

    List<GradeBookResponse> getStudentGradeBook(Long studentId, String schoolYear);

}
