package com.example.Quan_Ly_Hoc_Sinh_Backend.service.AcademicRecord;

import com.example.Quan_Ly_Hoc_Sinh_Backend.dto.AcademicRecordDTOs.AcademicRecordRequest;
import com.example.Quan_Ly_Hoc_Sinh_Backend.dto.AcademicRecordDTOs.AcademicRecordResponse;
import com.example.Quan_Ly_Hoc_Sinh_Backend.mapper.AcademicRecordMapper;
import com.example.Quan_Ly_Hoc_Sinh_Backend.model.Entity.AcademicRecord;
import com.example.Quan_Ly_Hoc_Sinh_Backend.model.Entity.GradeBook;
import com.example.Quan_Ly_Hoc_Sinh_Backend.model.Entity.Student;
import com.example.Quan_Ly_Hoc_Sinh_Backend.repository.AcademicRecordRepository;
import com.example.Quan_Ly_Hoc_Sinh_Backend.repository.GradeBookRepository;
import com.example.Quan_Ly_Hoc_Sinh_Backend.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
    @Transactional
    public AcademicRecordResponse createAcademicRecord(AcademicRecordRequest request) {
        // 1. Tìm Student bằng Code
        Student student = studentRepository.findByStudentCode(request.getStudentCode())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy học sinh với mã: " + request.getStudentCode()));

        // 2. Kiểm tra tồn tại
        academicRecordRepository.findByStudentIdAndSchoolYear(student.getId(), request.getSchoolYear())
                .ifPresent(s -> { throw new RuntimeException("Học bạ cho năm học này đã tồn tại!"); });

        // 3. Xử lý
        AcademicRecord record = academicRecordMapper.toEntity(request);
        record.setStudent(student);

        // Gọi hàm tính tổng điểm thay vì hàm calculateRating trống
        calculateTotalScores(record);

        return academicRecordMapper.toResponse(academicRecordRepository.save(record));
    }

    @Override
    @Transactional
    public AcademicRecordResponse updateAcademicRecord(AcademicRecordRequest request) {
        // Cần tìm studentId từ code trước
        Student student = studentRepository.findByStudentCode(request.getStudentCode())
                .orElseThrow(() -> new RuntimeException("Mã học sinh không tồn tại"));

        // Tìm học bạ dựa trên ID học sinh và năm học
        AcademicRecord existing = academicRecordRepository.findByStudentIdAndSchoolYear(student.getId(), request.getSchoolYear())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy học bạ để cập nhật"));

        existing.setConduct(request.getConduct());
        existing.setHomeroomTeacherReview(request.getHomeroomTeacherReview());
        existing.setParentFeedback(request.getParentFeedback());
        existing.setStatus(request.getStatus());

        // Sử dụng hàm tính toán tổng điểm
        calculateTotalScores(existing);

        return academicRecordMapper.toResponse(academicRecordRepository.save(existing));
    }

    @Override
    @Transactional
    public AcademicRecordResponse syncGrades(AcademicRecordRequest request) {
        Student student = studentRepository.findByStudentCode(request.getStudentCode())
                .orElseThrow(() -> new RuntimeException("Mã học sinh không tồn tại"));

        AcademicRecord record = academicRecordRepository.findByStudentIdAndSchoolYear(student.getId(), request.getSchoolYear())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy học bạ để đồng bộ điểm"));

        calculateTotalScores(record);
        return academicRecordMapper.toResponse(academicRecordRepository.save(record));
    }
    // --- TÌM KIẾM LỊCH SỬ THEO CODE ---
    @Override
    @Transactional(readOnly = true)
    public List<AcademicRecordResponse> getStudentHistory(String studentCode) {
        Student student = studentRepository.findByStudentCode(studentCode)
                .orElseThrow(() -> new RuntimeException("Mã học sinh không tồn tại"));

        return academicRecordRepository.findByStudentIdOrderBySchoolYearDesc(student.getId())
                .stream().map(academicRecordMapper::toResponse).collect(Collectors.toList());
    }

    // --- TÌM KIẾM CHI TIẾT THEO CODE + NĂM ---
    @Override
    @Transactional(readOnly = true)
    public AcademicRecordResponse getRecordByStudentCodeAndYear(String studentCode, String schoolYear) {
        Student student = studentRepository.findByStudentCode(studentCode)
                .orElseThrow(() -> new RuntimeException("Mã học sinh không tồn tại"));

        AcademicRecord record = academicRecordRepository.findByStudentIdAndSchoolYear(student.getId(), schoolYear)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy học bạ năm " + schoolYear));
        return academicRecordMapper.toResponse(record);
    }

    // --- XÓA THEO CODE + NĂM ---
    @Override
    @Transactional
    public void deleteRecordByCode(String studentCode, String schoolYear) {
        Student student = studentRepository.findByStudentCode(studentCode)
                .orElseThrow(() -> new RuntimeException("Mã học sinh không tồn tại"));

        AcademicRecord record = academicRecordRepository.findByStudentIdAndSchoolYear(student.getId(), schoolYear)
                .orElseThrow(() -> new RuntimeException("Học bạ không tồn tại để xóa"));

        academicRecordRepository.delete(record);
    }
    // --- HÀM TÍNH TOÁN ---
    private void calculateTotalScores(AcademicRecord record) {
        List<GradeBook> subjectGrades = gradeBookRepository
                .findByStudentIdAndSchoolYear(record.getStudent().getId(), record.getSchoolYear());

        if (!subjectGrades.isEmpty()) {
            BigDecimal totalScore = subjectGrades.stream()
                    .map(gb -> gb.getAverageScore() != null ? gb.getAverageScore() : BigDecimal.ZERO)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            BigDecimal avgYear = totalScore.divide(BigDecimal.valueOf(subjectGrades.size()), 2, RoundingMode.HALF_UP);
            record.setYearAverageScore(avgYear);
            record.setAcademicPerformanceRating(calculateRating(avgYear));
        }
    }

    private String calculateRating(BigDecimal score) {
        double val = score.doubleValue();
        if (val >= 8.0) return "GIỎI";
        if (val >= 6.5) return "KHÁ";
        if (val >= 5.0) return "TRUNG BÌNH";
        return "YẾU";
    }
}