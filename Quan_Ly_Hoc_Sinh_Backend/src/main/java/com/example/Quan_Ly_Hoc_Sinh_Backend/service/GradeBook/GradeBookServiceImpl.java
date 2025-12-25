package com.example.Quan_Ly_Hoc_Sinh_Backend.service.GradeBook;

import com.example.Quan_Ly_Hoc_Sinh_Backend.dto.GradeBookDTOs.GradeBookRequest;
import com.example.Quan_Ly_Hoc_Sinh_Backend.dto.GradeBookDTOs.GradeBookResponse;
import com.example.Quan_Ly_Hoc_Sinh_Backend.mapper.GradeBookMapper;
import com.example.Quan_Ly_Hoc_Sinh_Backend.model.Entity.GradeBook;
import com.example.Quan_Ly_Hoc_Sinh_Backend.model.Entity.ScoreEntry;
import com.example.Quan_Ly_Hoc_Sinh_Backend.model.Enum.ESemester;
import com.example.Quan_Ly_Hoc_Sinh_Backend.repository.GradeBookRepository;
import com.example.Quan_Ly_Hoc_Sinh_Backend.repository.ScoreEntryRepository;
import com.example.Quan_Ly_Hoc_Sinh_Backend.repository.StudentRepository;
import com.example.Quan_Ly_Hoc_Sinh_Backend.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GradeBookServiceImpl implements GradeBookService {
    @Autowired
    private GradeBookRepository gradeBookRepository;

    @Autowired
    private ScoreEntryRepository scoreEntryRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private GradeBookMapper gradeBookMapper;

    @Override
    @Transactional
    public void calculateAndSaveAverage(Long studentId, Long subjectId, ESemester semester, String schoolYear) {
        List<ScoreEntry> scores = scoreEntryRepository.findByStudentIdAndSubjectId(studentId, subjectId);

        // Tìm bản ghi GradeBook hiện tại
        GradeBook gradeBook = gradeBookRepository
                .findByStudentIdAndSubjectIdAndSemester(studentId, subjectId, semester)
                .orElse(null);

        // Nếu không còn điểm nào, xóa hoặc reset bảng điểm
        if (scores.isEmpty()) {
            if (gradeBook != null) {
                gradeBook.setAverageScore(BigDecimal.ZERO);
                gradeBook.setRating("N/A");
                gradeBookRepository.save(gradeBook);
            }
            return;
        }

        BigDecimal totalPoints = BigDecimal.ZERO;
        BigDecimal totalWeights = BigDecimal.ZERO;

        for (ScoreEntry s : scores) {
            BigDecimal weight = getWeightByScoreType(s.getScoreType().name());
            totalPoints = totalPoints.add(s.getScoreValue().multiply(weight));
            totalWeights = totalWeights.add(weight);
        }

        BigDecimal average = totalPoints.divide(totalWeights, 2, RoundingMode.HALF_UP);

        if (gradeBook == null) {
            gradeBook = new GradeBook();
            gradeBook.setStudent(studentRepository.findById(studentId).orElseThrow());
            gradeBook.setSubject(subjectRepository.findById(subjectId).orElseThrow());
            gradeBook.setSemester(semester);
            gradeBook.setSchoolYear(schoolYear);
        }

        gradeBook.setAverageScore(average);
        gradeBook.setRating(determineRating(average));

        gradeBookRepository.save(gradeBook);
    }

    @Override
    @Transactional
    public GradeBookResponse saveManual(GradeBookRequest request) {
        // Tìm bản ghi cũ để cập nhật thay vì luôn tạo mới, tránh lỗi trùng Unique Constraint
        GradeBook entity = gradeBookRepository
                .findByStudentIdAndSubjectIdAndSemester(request.getStudentId(), request.getSubjectId(), request.getSemester())
                .orElse(gradeBookMapper.toEntity(request));

        entity.setStudent(studentRepository.findById(request.getStudentId()).orElseThrow());
        entity.setSubject(subjectRepository.findById(request.getSubjectId()).orElseThrow());
        entity.setAverageScore(request.getAverageScore());
        entity.setRating(request.getRating());

        GradeBook saved = gradeBookRepository.save(entity);
        return gradeBookMapper.toResponse(saved);
    }

    @Override
    public List<GradeBookResponse> getStudentGradeBook(Long studentId, String schoolYear) {
        return gradeBookRepository.findByStudentIdAndSchoolYear(studentId, schoolYear)
                .stream().map(gradeBookMapper::toResponse).collect(Collectors.toList());
    }

    private BigDecimal getWeightByScoreType(String type) {
        return switch (type) {
            case "ORAL_TEST", "FIFTEEN_MIN_TEST" -> new BigDecimal("1");
            case "FORTY_FIVE_MIN_TEST" -> new BigDecimal("2");
            case "SEMESTER_EXAM" -> new BigDecimal("3");
            default -> BigDecimal.ONE;
        };
    }

    private String determineRating(BigDecimal avg) {
        double val = avg.doubleValue();
        if (val >= 8.0) return "Giỏi";
        if (val >= 6.5) return "Khá";
        if (val >= 5.0) return "Trung Bình";
        return "Yếu";
    }
}