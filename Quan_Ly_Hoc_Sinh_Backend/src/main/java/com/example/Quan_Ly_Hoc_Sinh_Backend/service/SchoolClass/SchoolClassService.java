package com.example.Quan_Ly_Hoc_Sinh_Backend.service.SchoolClass;

import com.example.Quan_Ly_Hoc_Sinh_Backend.dto.SchoolClassDTOs.SchoolClassRequest;
import com.example.Quan_Ly_Hoc_Sinh_Backend.dto.SchoolClassDTOs.SchoolClassResponse;

import java.util.List;

public interface SchoolClassService {
    SchoolClassResponse createClass(SchoolClassRequest request);
    List<SchoolClassResponse> getAllClasses();
}
