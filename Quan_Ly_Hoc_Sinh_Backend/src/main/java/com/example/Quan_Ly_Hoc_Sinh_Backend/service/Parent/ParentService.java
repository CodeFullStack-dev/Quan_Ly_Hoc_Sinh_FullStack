package com.example.Quan_Ly_Hoc_Sinh_Backend.service.Parent;

import com.example.Quan_Ly_Hoc_Sinh_Backend.dto.ParentDTOs.ParentRequest;
import com.example.Quan_Ly_Hoc_Sinh_Backend.dto.ParentDTOs.ParentResponse;
import com.example.Quan_Ly_Hoc_Sinh_Backend.model.Entity.Parent;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ParentService {
    ParentResponse addParentToStudent(ParentRequest request);
    ParentResponse updateParent(Long id, ParentRequest request);
    //Tìm kiếm tất cả Parent cùng tên
    List<ParentResponse> getAllParentsInfo(String query);
}
