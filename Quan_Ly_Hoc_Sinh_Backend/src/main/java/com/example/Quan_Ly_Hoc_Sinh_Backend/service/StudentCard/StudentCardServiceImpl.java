package com.example.Quan_Ly_Hoc_Sinh_Backend.service.StudentCard;

import com.example.Quan_Ly_Hoc_Sinh_Backend.dto.StudentCardDTOs.StudentCardResponse;
import com.example.Quan_Ly_Hoc_Sinh_Backend.mapper.StudentCardMapper;
import com.example.Quan_Ly_Hoc_Sinh_Backend.model.Entity.Student;
import com.example.Quan_Ly_Hoc_Sinh_Backend.model.Entity.StudentCard;
import com.example.Quan_Ly_Hoc_Sinh_Backend.repository.StudentCardRepository;
import com.example.Quan_Ly_Hoc_Sinh_Backend.repository.StudentRepository;
import com.example.Quan_Ly_Hoc_Sinh_Backend.service.UploadImage.UploadImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Calendar;
import java.util.Date;

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
    @Transactional
    public StudentCardResponse issueCard(String studentCode, MultipartFile file) {
        Student student = studentRepository.findByStudentCode(studentCode)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy học sinh"));

        if(studentCardRepository.existsByStudentCode(studentCode)) {
            throw new RuntimeException("Học sinh này đã có thẻ rồi");
        }

        String photoUrl = uploadImageService.uploadImage(file, "card_" + studentCode);

        // 4. Khởi tạo Entity mới
        StudentCard card = new StudentCard();
        card.setStudent(student);
        card.setPhotoUrl(photoUrl);
        card.setCardNumber("CARD-" + studentCode.toUpperCase() + "-" + System.currentTimeMillis() / 1000);
        card.setStatus("ACTIVE");

        // Ngày cấp và 4 năm sau hết hạn
        Date now = new Date();
        card.setIssueDate(now);
        Calendar cal = Calendar.getInstance();
        cal.setTime(now);
        cal.add(Calendar.YEAR, 4);
        card.setExpiryDate(cal.getTime());

        return studentCardMapper.toResponse(studentCardRepository.save(card));
    }

    @Override
    @Transactional
    public StudentCardResponse getCardByStudentCode(String studentCode) {
        Student student = studentRepository.findByStudentCode(studentCode)
                .orElseThrow(() -> new RuntimeException("Mã học sinh không tồn tại"));

        StudentCard card = studentCardRepository.findByStudentCode(studentCode)
                .orElseThrow(() -> new RuntimeException("Học sinh này chưa được cấp thẻ"));

        return studentCardMapper.toResponse(card);
    }

    @Override
    public StudentCardResponse updateCardStatus(String studentCode, String status) {
        Student student = studentRepository.findByStudentCode(studentCode)
                .orElseThrow(() -> new RuntimeException("Mã học sinh không tồn tại"));

        StudentCard card = studentCardRepository.findByStudentCode(studentCode)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy thẻ của học sinh này"));

        card.setStatus(status.toUpperCase());
        return studentCardMapper.toResponse(studentCardRepository.save(card));
    }

    @Override
    public void deleteCard(String studentCode) {
        Student student = studentRepository.findByStudentCode(studentCode)
                .orElseThrow(() -> new RuntimeException("Mã học sinh không tồn tại"));

        StudentCard card = studentCardRepository.findByStudentCode(studentCode)
                .orElseThrow(() -> new RuntimeException("Thẻ không tồn tại"));

        // Xóa ảnh trên Cloudinary trước khi xóa DB để tiết kiệm dung lượng
        if (card.getPhotoUrl() != null) {
            uploadImageService.deleteImage(card.getPhotoUrl());
        }

        studentCardRepository.delete(card);
    }
}
