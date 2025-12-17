package com.example.Quan_Ly_Hoc_Sinh_Backend.model.Entity;

import com.example.Quan_Ly_Hoc_Sinh_Backend.model.Enum.EScoreType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "score_entry")
public class ScoreEntry {

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

    // Loại bài kiểm tra (sử dụng Enum)
    @Enumerated(EnumType.STRING)
    @Column(name = "score_type", nullable = false)
    private EScoreType scoreType;

    // Điểm số (nên dùng BigDecimal để đảm bảo độ chính xác)
    @Column(name = "score_value", precision = 3, scale = 1, nullable = false) // Ví dụ: 10.0
    private BigDecimal scoreValue;

    @Temporal(TemporalType.DATE)
    @Column(name = "test_date", nullable = false)
    private Date testDate;

    // Mối quan hệ N-1: Giáo viên nhập điểm
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id")
    private Employee teacher;
}
