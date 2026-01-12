package com.example.Quan_Ly_Hoc_Sinh_Backend.mapper;

import com.example.Quan_Ly_Hoc_Sinh_Backend.dto.ParentDTOs.ParentRequest;
import com.example.Quan_Ly_Hoc_Sinh_Backend.dto.ParentDTOs.ParentResponse;
import com.example.Quan_Ly_Hoc_Sinh_Backend.model.Entity.Parent;
import org.springframework.stereotype.Component;

@Component
public class ParentMapper {
    public Parent toEntity(ParentRequest request){
        Parent parent = new Parent();
        parent.setFullName(request.getFullName());
        parent.setPhoneNumber(request.getPhoneNumber());
        parent.setEmail(request.getEmail());
        parent.setOccupation(request.getJob());
        parent.setAddress(request.getAddress());
        return parent;
    }

    public ParentResponse toResponse(Parent parent) {
        ParentResponse response = new ParentResponse();
        response.setId(parent.getId());
        response.setFullName(parent.getFullName());
        response.setPhoneNumber(parent.getPhoneNumber());
        response.setEmail(parent.getEmail());
        return response;
    }
}
