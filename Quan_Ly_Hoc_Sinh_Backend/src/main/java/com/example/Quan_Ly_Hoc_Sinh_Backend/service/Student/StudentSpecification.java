package com.example.Quan_Ly_Hoc_Sinh_Backend.service.Student;

import com.example.Quan_Ly_Hoc_Sinh_Backend.model.Entity.Student;
import com.example.Quan_Ly_Hoc_Sinh_Backend.model.Enum.EGender;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.criteria.Predicate;

public class StudentSpecification {
    public static Specification<Student> filterStudents(String name, Long classId, String gender) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            // Lọc theo tên (không phân biệt hoa thường)
            if (name != null && !name.isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get("fullName")), "%" + name.toLowerCase() + "%"));
            }

            // Lọc theo ID lớp (truy cập qua thuộc tính schoolClass)
            if (classId != null) {
                predicates.add(cb.equal(root.get("schoolClass").get("id"), classId));
            }

            // Lọc theo giới tính
            if (gender != null && !gender.isEmpty()) {
                predicates.add(cb.equal(root.get("gender"), EGender.valueOf(gender)));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
    public static Specification<Student> hasStudentCode(String code) {
        return (root, query, cb) -> code == null || code.isEmpty() ? null :
                cb.equal(root.get("studentCode"), code);
    }
}
