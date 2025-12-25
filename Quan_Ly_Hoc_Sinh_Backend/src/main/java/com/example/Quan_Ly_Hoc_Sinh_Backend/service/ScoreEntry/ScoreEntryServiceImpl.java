package com.example.Quan_Ly_Hoc_Sinh_Backend.service.ScoreEntry;

import com.example.Quan_Ly_Hoc_Sinh_Backend.dto.ScoreEntryDTOs.ScoreEntryRequest;
import com.example.Quan_Ly_Hoc_Sinh_Backend.dto.ScoreEntryDTOs.ScoreEntryResponse;
import com.example.Quan_Ly_Hoc_Sinh_Backend.mapper.ScoreEntryMapper;
import com.example.Quan_Ly_Hoc_Sinh_Backend.model.Entity.ScoreEntry;
import com.example.Quan_Ly_Hoc_Sinh_Backend.model.Enum.ESemester;
import com.example.Quan_Ly_Hoc_Sinh_Backend.repository.EmployeeRepository;
import com.example.Quan_Ly_Hoc_Sinh_Backend.repository.ScoreEntryRepository;
import com.example.Quan_Ly_Hoc_Sinh_Backend.repository.StudentRepository;
import com.example.Quan_Ly_Hoc_Sinh_Backend.repository.SubjectRepository;
import com.example.Quan_Ly_Hoc_Sinh_Backend.service.GradeBook.GradeBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScoreEntryServiceImpl implements ScoreEntryService {

    @Autowired
    private ScoreEntryRepository scoreEntryRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ScoreEntryMapper scoreEntryMapper;
    @Autowired
    private GradeBookService gradeBookService;

    @Override
    @Transactional
    public ScoreEntryResponse createScore(ScoreEntryRequest request) {
        ScoreEntry entity = scoreEntryMapper.toEntity(request);

        entity.setStudent(studentRepository.findById(request.getStudentId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy học sinh")));
        entity.setSubject(subjectRepository.findById(request.getSubjectId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy môn học")));
        entity.setTeacher(employeeRepository.findById(request.getTeacherId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy giáo viên")));

        ScoreEntry saved = scoreEntryRepository.save(entity);

        // Kích hoạt tính toán lại điểm trung bình
        gradeBookService.calculateAndSaveAverage(
                request.getStudentId(),
                request.getSubjectId(),
                request.getSemester(),
                request.getSchoolYear()
        );

        return scoreEntryMapper.toResponse(saved);
    }

    @Override
    @Transactional
    public ScoreEntryResponse updateScore(Long id, ScoreEntryRequest request) {
        ScoreEntry existing = scoreEntryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy đầu điểm"));

        existing.setScoreValue(request.getScoreValue());
        existing.setScoreType(request.getScoreType());
        existing.setTestDate(request.getTestDate());

        ScoreEntry updated = scoreEntryRepository.save(existing);

        // Luôn tính lại điểm TB sau khi cập nhật điểm thành phần
        gradeBookService.calculateAndSaveAverage(
                updated.getStudent().getId(),
                updated.getSubject().getId(),
                request.getSemester(),
                request.getSchoolYear()
        );

        return scoreEntryMapper.toResponse(updated);
    }

    @Override
    @Transactional
    public void deleteScore(Long id, ESemester semester, String schoolYear) {
        ScoreEntry score = scoreEntryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy đầu điểm để xóa"));

        Long studentId = score.getStudent().getId();
        Long subjectId = score.getSubject().getId();

        scoreEntryRepository.delete(score);

        // Sau khi xóa, phải tính lại điểm TB ngay
        gradeBookService.calculateAndSaveAverage(studentId, subjectId, semester, schoolYear);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ScoreEntryResponse> getScoresByStudentCodeAndSubjectCode(String studentCode, String subjectCode) {
        // 1. Tìm học sinh từ mã (findByStudentCode đã có trong StudentRepository)
        var student = studentRepository.findByStudentCode(studentCode)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy học sinh mã: " + studentCode));

        // 2. Tìm môn học từ mã (findBySubjectCode đã có trong SubjectRepository)
        var subject = subjectRepository.findBySubjectCode(subjectCode)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy môn học mã: " + subjectCode));

        // 3. Sử dụng ID của chúng để lấy danh sách điểm
        return scoreEntryRepository.findByStudentIdAndSubjectId(student.getId(), subject.getId())
                .stream()
                .map(scoreEntryMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ScoreEntryResponse> getScoresByStudentAndSubject(Long studentId, Long subjectId) {
        return scoreEntryRepository.findByStudentIdAndSubjectId(studentId, subjectId)
                .stream().map(scoreEntryMapper::toResponse).collect(Collectors.toList());
    }
}
