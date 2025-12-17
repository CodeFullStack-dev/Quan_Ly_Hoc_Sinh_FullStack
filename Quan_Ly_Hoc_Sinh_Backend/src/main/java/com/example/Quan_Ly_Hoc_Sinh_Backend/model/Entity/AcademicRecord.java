package com.example.Quan_Ly_Hoc_Sinh_Backend.model.Entity;

import com.example.Quan_Ly_Hoc_Sinh_Backend.model.Enum.EConduct;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
// Đảm bảo mỗi Học sinh chỉ có 1 bản ghi Sổ học bạ cho 1 Niên khóa
@Table(name = "academic_record", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"student_id", "school_year"})
})
public class AcademicRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Mối quan hệ N-1: Học sinh nào
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @Column(name = "school_year", nullable = false, length = 9)
    private String schoolYear; // Niên khóa

    // Hạnh kiểm (sử dụng Enum)
    @Enumerated(EnumType.STRING)
    @Column(name = "conduct", nullable = false, length = 20)
    private EConduct conduct;

    // Điểm trung bình cả năm (có thể tổng hợp từ GradeBook)
    @Column(name = "year_average_score", precision = 3, scale = 2)
    private BigDecimal yearAverageScore;

    // Xếp loại học lực
    @Column(name = "academic_performance_rating", length = 50)
    private String academicPerformanceRating;

    // Nhận xét của Giáo viên Chủ nhiệm
    @Lob // Dùng @Lob cho nội dung lớn
    @Column(name = "homeroom_teacher_review")
    private String homeroomTeacherReview;

    // Nhận xét của Phụ huynh
    @Lob
    @Column(name = "parent_feedback")
    private String parentFeedback;

    // Trạng thái (Ví dụ: Đã hoàn tất/Chưa hoàn tất)
    @Column(name = "status", length = 30)
    private String status;
}