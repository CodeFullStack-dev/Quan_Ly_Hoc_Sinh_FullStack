package com.example.Quan_Ly_Hoc_Sinh_Backend.service.AcademicRecord;

import com.example.Quan_Ly_Hoc_Sinh_Backend.dto.AcademicRecordDTOs.AcademicRecordRequest;
import com.example.Quan_Ly_Hoc_Sinh_Backend.dto.AcademicRecordDTOs.AcademicRecordResponse;
import com.example.Quan_Ly_Hoc_Sinh_Backend.mapper.AcademicRecordMapper;
import com.example.Quan_Ly_Hoc_Sinh_Backend.model.Entity.AcademicRecord;
import com.example.Quan_Ly_Hoc_Sinh_Backend.model.Entity.GradeBook;
import com.example.Quan_Ly_Hoc_Sinh_Backend.repository.AcademicRecordRepository;
import com.example.Quan_Ly_Hoc_Sinh_Backend.repository.GradeBookRepository;
import com.example.Quan_Ly_Hoc_Sinh_Backend.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AcademicRecordServiceImpl implements AcademicRecordService {

    @Autowired
    private AcademicRecordRepository academicRecordRepository;

    @Autowired
    private GradeBookRepository gradeBookRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private AcademicRecordMapper academicRecordMapper;

    @Override
    public AcademicRecordResponse generateOrUpdateRecord(AcademicRecordRequest request) {
        AcademicRecord academicRecord = academicRecordRepository
                .findByStudentIdAndSchoolYear(request.getStudentId(), request.getSchoolYear())
                .orElseGet(() -> {
                    AcademicRecord newRecord = academicRecordMapper.toEntity(request);
                    newRecord.setStudent(studentRepository.findById(request.getStudentId())
                            .orElseThrow(() -> new RuntimeException("Không tìm thấy học sinh")));
                    return newRecord;
                });

        //Cập nhật thông tin từ request
        academicRecord.setConduct(request.getConduct());
        academicRecord.setHomeroomTeacherReview(request.getHomeroomTeacherReview());
        academicRecord.setParentFeedback(request.getParentFeedback());
        academicRecord.setStatus(request.getStatus());

        // LOGIC TỔNG HỢP: Lấy tất cả GradeBook của học sinh trong năm học đó
        List<GradeBook> subjectGrades = gradeBookRepository
                .findByStudentIdAndSchoolYear(request.getStudentId(), request.getSchoolYear());

        if (!subjectGrades.isEmpty()) {
            BigDecimal totalScore = subjectGrades.stream()
                    .map(gb -> gb.getAverageScore() != null ? gb.getAverageScore() : BigDecimal.ZERO)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            BigDecimal avgYear = totalScore.divide(BigDecimal.valueOf(subjectGrades.size()), 2, BigDecimal.ROUND_HALF_UP);
            academicRecord.setYearAverageScore(avgYear);
            academicRecord.setAcademicPerformanceRating(calculateRating(avgYear));
        }

        return academicRecordMapper.toResponse(academicRecordRepository.save(academicRecord));
    }

    // --- TÌM KIẾM ---
    @Override
    @Transactional(readOnly = true)
    public List<AcademicRecordResponse> getStudentHistory(Long studentId) {
        return academicRecordRepository.findByStudentIdOrderBySchoolYearDesc(studentId)
                .stream().map(academicRecordMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public AcademicRecordResponse getRecordByStudentAndYear(Long studentId, String schoolYear) {
        AcademicRecord record = academicRecordRepository.findByStudentIdAndSchoolYear(studentId, schoolYear)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy học bạ năm " + schoolYear));
        return academicRecordMapper.toResponse(record);
    }

    @Override
    @Transactional
    public void deleteRecord(Long id) {
        if (!academicRecordRepository.existsById(id)) {
            throw new RuntimeException("Học bạ không tồn tại");
        }
        academicRecordRepository.deleteById(id);
    }

    // Hàm phụ trợ tính xếp loại
    private String calculateRating(BigDecimal score) {
        double val = score.doubleValue();
        if (val >= 8.0) return "GIỎI";
        if (val >= 6.5) return "KHÁ";
        if (val >= 5.0) return "TRUNG BÌNH";
        return "YẾU";
    }
}
