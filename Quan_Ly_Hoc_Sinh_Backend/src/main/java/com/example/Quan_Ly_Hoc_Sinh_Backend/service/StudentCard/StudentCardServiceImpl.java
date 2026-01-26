package com.example.Quan_Ly_Hoc_Sinh_Backend.service.StudentCard;

import com.example.Quan_Ly_Hoc_Sinh_Backend.dto.StudentCardDTOs.StudentCardResponse;
import com.example.Quan_Ly_Hoc_Sinh_Backend.mapper.StudentCardMapper;
import com.example.Quan_Ly_Hoc_Sinh_Backend.repository.StudentCardRepository;
import com.example.Quan_Ly_Hoc_Sinh_Backend.repository.StudentRepository;
import com.example.Quan_Ly_Hoc_Sinh_Backend.service.UploadImage.UploadImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class StudentCardServiceImpl implements StudentCardService {

    @Autowired
    private final StudentCardRepository studentCardRepository;

    @Autowired
    private final StudentRepository studentRepository;

    @Autowired
    private final UploadImageService uploadImageService;

    @Autowired
    private final StudentCardMapper studentCardMapper;

    @Override
    public StudentCardResponse issueCard(String studentCode, MultipartFile file) {
        return null;
    }

    @Override
    public StudentCardResponse getCardByStudentCode(String studentCode) {
        return null;
    }

    @Override
    public StudentCardResponse updateCardStatus(String studentCode, String status) {
        return null;
    }

    @Override
    public void deleteCard(String studentCode) {

    }
}
