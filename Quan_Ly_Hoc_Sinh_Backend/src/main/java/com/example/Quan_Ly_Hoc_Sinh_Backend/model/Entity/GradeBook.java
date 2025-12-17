package com.example.Quan_Ly_Hoc_Sinh_Backend.model.Entity;

import com.example.Quan_Ly_Hoc_Sinh_Backend.model.Enum.ESemester;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
// Thêm Unique Constraint để đảm bảo mỗi Học sinh chỉ có 1 bản ghi/Môn/Kỳ
@Table(name = "grade_book", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"student_id", "subject_id", "semester"})
})
public class GradeBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Mối quan hệ N-1: Học sinh nào
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    // Mối quan hệ N-1: Môn học nào
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

    // Kỳ học
    @Enumerated(EnumType.STRING)
    @Column(name = "semester", nullable = false, length = 20)
    private ESemester semester;

    @Column(name = "school_year", nullable = false, length = 9)
    private String schoolYear;

    // Điểm trung bình môn (được tính toán từ ScoreEntry)
    @Column(name = "average_score", precision = 3, scale = 2)
    private BigDecimal averageScore;

    // Đánh giá/xếp loại cuối kỳ
    @Column(name = "rating", length = 50)
    private String rating;
}