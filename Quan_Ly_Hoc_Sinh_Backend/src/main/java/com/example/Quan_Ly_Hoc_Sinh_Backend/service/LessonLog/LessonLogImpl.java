package com.example.Quan_Ly_Hoc_Sinh_Backend.service.LessonLog;

import com.example.Quan_Ly_Hoc_Sinh_Backend.dto.LessonLogDTOs.LessonLogRequest;
import com.example.Quan_Ly_Hoc_Sinh_Backend.dto.LessonLogDTOs.LessonLogResponse;
import com.example.Quan_Ly_Hoc_Sinh_Backend.mapper.LessonLogMapper;
import com.example.Quan_Ly_Hoc_Sinh_Backend.model.Entity.Employee;
import com.example.Quan_Ly_Hoc_Sinh_Backend.model.Entity.LessonLog;
import com.example.Quan_Ly_Hoc_Sinh_Backend.model.Entity.SchoolClass;
import com.example.Quan_Ly_Hoc_Sinh_Backend.model.Entity.Subject;
import com.example.Quan_Ly_Hoc_Sinh_Backend.repository.EmployeeRepository;
import com.example.Quan_Ly_Hoc_Sinh_Backend.repository.LessonLogRepository;
import com.example.Quan_Ly_Hoc_Sinh_Backend.repository.SchoolClassRepository;
import com.example.Quan_Ly_Hoc_Sinh_Backend.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LessonLogImpl implements LessonLogService {

    @Autowired
    private LessonLogRepository lessonLogRepository;

    @Autowired
    private SchoolClassRepository schoolClassRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private LessonLogMapper lessonLogMapper;

    @Override
    public LessonLogResponse createLog(LessonLogRequest request) {
        LessonLog lessonLog = lessonLogMapper.toEntity(request);

        SchoolClass schoolClass = schoolClassRepository.findById(request.getClassId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy lớp học"));
        Employee teacher = employeeRepository.findById(request.getTeacherId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy giáo viên"));
        Subject subject = subjectRepository.findById(request.getSubjectId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy môn học"));

        lessonLog.setSchoolClass(schoolClass);
        lessonLog.setTeacher(teacher);
        lessonLog.setSubject(subject);

        return lessonLogMapper.toResponse(lessonLogRepository.save(lessonLog));
    }

    @Override
    public List<LessonLogResponse> getLogsByClass(Long classId) {
        return lessonLogRepository.findBySchoolClassId(classId).stream()
                .map(lessonLogMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteLog(Long id) {
        lessonLogRepository.deleteById(id);
    }
}
