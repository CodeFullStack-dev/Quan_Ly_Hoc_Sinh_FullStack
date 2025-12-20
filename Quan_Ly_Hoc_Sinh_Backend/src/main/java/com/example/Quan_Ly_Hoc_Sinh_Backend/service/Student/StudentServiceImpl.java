package com.example.Quan_Ly_Hoc_Sinh_Backend.service.Student;

import com.example.Quan_Ly_Hoc_Sinh_Backend.dto.StudentDTOs.StudentRequest;
import com.example.Quan_Ly_Hoc_Sinh_Backend.dto.StudentDTOs.StudentResponse;
import com.example.Quan_Ly_Hoc_Sinh_Backend.mapper.StudentMapper;
import com.example.Quan_Ly_Hoc_Sinh_Backend.model.Entity.AcademicRecord;
import com.example.Quan_Ly_Hoc_Sinh_Backend.model.Entity.SchoolClass;
import com.example.Quan_Ly_Hoc_Sinh_Backend.model.Entity.Student;
import com.example.Quan_Ly_Hoc_Sinh_Backend.model.Entity.StudentCard;
import com.example.Quan_Ly_Hoc_Sinh_Backend.model.Enum.EConduct;
import com.example.Quan_Ly_Hoc_Sinh_Backend.model.Enum.EGender;
import com.example.Quan_Ly_Hoc_Sinh_Backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentCardRepository studentCardRepository;

    @Autowired
    private AcademicRecordRepository academicRecordRepository;

    @Autowired
    private SchoolClassRepository schoolClassRepository;

    @Autowired
    private StudentMapper studentMapper; // Tiêm Mapper vào đây

    @Override
    public List<StudentResponse> searchStudents(String name, Long classId, String gender) {
        // Gọi Specification đã viết ở trên
        Specification<Student> spec = StudentSpecification.filterStudents(name, classId, gender);

        // findAll(spec) được cung cấp bởi JpaSpecificationExecutor
        return studentRepository.findAll(spec)
                .stream()
                .map(studentMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public StudentResponse createStudent(StudentRequest request) {
        // 1. Kiểm tra lớp học
        SchoolClass schoolClass = schoolClassRepository.findById(request.getSchoolClassId())
                .orElseThrow(() -> new RuntimeException("Lớp học không tồn tại"));

        // 2. Map từ DTO sang Entity
        Student student = studentMapper.toEntity(request);
        student.setSchoolClass(schoolClass);

        Student savedStudent = studentRepository.save(student);

        // 3. Tự động tạo thẻ học sinh
        StudentCard card = new StudentCard();
        card.setStudent(savedStudent);
        card.setCardNumber("CARD-" + savedStudent.getStudentCode());
        card.setIssueDate(new Date());
        card.setStatus("ACTIVE");
        studentCardRepository.save(card);

        // 4. Tự động tạo học bạ cho niên khóa của lớp học
        AcademicRecord record = new AcademicRecord();
        record.setStudent(savedStudent);
        record.setSchoolYear(schoolClass.getSchoolYear());
        record.setConduct(EConduct.AVERAGE); // Mặc định hạnh kiểm TB khi mới vào
        academicRecordRepository.save(record);

        return studentMapper.toResponse(savedStudent);
    }

    @Override
    @Transactional
    public StudentResponse updateStudent(StudentRequest request) {
        Student student = studentRepository.findByStudentCode(request.getStudentCode())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy mã học sinh: " + request.getStudentCode()));

        // Cập nhật các thông tin cơ bản
        student.setFullName(request.getFullName());
        student.setDateOfBirth(request.getDateOfBirth());
        student.setGender(EGender.valueOf(request.getGender()));
        student.setCurrentAddress(request.getCurrentAddress());

        return studentMapper.toResponse(studentRepository.save(student));
    }

    @Override
    @Transactional
    public StudentResponse transferClass(Long studentId, Long newClassId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Học sinh không tồn tại"));

        SchoolClass newClass = schoolClassRepository.findById(newClassId)
                .orElseThrow(() -> new RuntimeException("Lớp mới không tồn tại"));

        // Chuyển lớp
        student.setSchoolClass(newClass);

        return studentMapper.toResponse(studentRepository.save(student));
    }

    @Override
    @Transactional
    public void deleteStudent(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy học sinh để xóa"));

        // Xóa student (Cascade sẽ tự xóa Thẻ và các bản ghi liên quan nếu đã cấu hình)
        studentRepository.delete(student);
    }

    @Override
    public StudentResponse getStudentByCode(String studentCode) {
        Student student = studentRepository.findByStudentCode(studentCode)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy học sinh có mã: " + studentCode));
        return studentMapper.toResponse(student);
    }
}

