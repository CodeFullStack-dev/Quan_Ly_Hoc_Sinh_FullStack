package com.example.Quan_Ly_Hoc_Sinh_Backend.service.School;

import com.example.Quan_Ly_Hoc_Sinh_Backend.dto.SchoolDTOs.SchoolRequest;
import com.example.Quan_Ly_Hoc_Sinh_Backend.dto.SchoolDTOs.SchoolResponse;
import com.example.Quan_Ly_Hoc_Sinh_Backend.mapper.SchoolMapper;
import com.example.Quan_Ly_Hoc_Sinh_Backend.model.Entity.School;
import com.example.Quan_Ly_Hoc_Sinh_Backend.repository.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
public class SchoolServiceImpl implements SchoolService{

    @Autowired
    private SchoolRepository schoolRepository;

    @Autowired
    private SchoolMapper schoolMapper;

    @Override
    public List<SchoolResponse> getAllSchools() {
        return schoolRepository.findAll()
                .stream()
                .map(schoolMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public SchoolResponse getSchoolById(Long id) {
        School school = schoolRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy trường với ID: " + id));


        return schoolMapper.toResponse(school);
    }

    @Override
    @Transactional
    public SchoolResponse createSchool(SchoolRequest request) {
        School school = schoolMapper.toEntity(request);
        return schoolMapper.toResponse(schoolRepository.save(school));
    }

    @Override
    @Transactional
    public SchoolResponse updateSchool(Long id, SchoolRequest request) {
        School existingSchool = schoolRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy trường để cập nhật!"));

        existingSchool.setSchoolName(request.getSchoolName());
        existingSchool.setAddress(request.getAddress());
        existingSchool.setPhoneNumber(request.getPhoneNumber());
        existingSchool.setEmail(request.getEmail());

        return schoolMapper.toResponse(schoolRepository.save(existingSchool));
    }

    @Override
    @Transactional
    public void deleteSchool(Long id) {
    if(!schoolRepository.existsById(id)) {
        throw new RuntimeException("ID không tồn tại");
    }
        schoolRepository.deleteById(id);

    }
}
