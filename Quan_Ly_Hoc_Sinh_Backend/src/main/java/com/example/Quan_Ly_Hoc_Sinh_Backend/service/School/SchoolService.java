package com.example.Quan_Ly_Hoc_Sinh_Backend.service.School;

import com.example.Quan_Ly_Hoc_Sinh_Backend.dto.SchoolDTOs.SchoolRequest;
import com.example.Quan_Ly_Hoc_Sinh_Backend.dto.SchoolDTOs.SchoolResponse;

import java.util.List;

public interface SchoolService {
    List<SchoolResponse> getAllSchools();
    SchoolResponse getSchoolById(Long id);
    SchoolResponse createSchool(SchoolRequest request);
    SchoolResponse updateSchool(Long id, SchoolRequest request);
    void deleteSchool(Long id);
}
