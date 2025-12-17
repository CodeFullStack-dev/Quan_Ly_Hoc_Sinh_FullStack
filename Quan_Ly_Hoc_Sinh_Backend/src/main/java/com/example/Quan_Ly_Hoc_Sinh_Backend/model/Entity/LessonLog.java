package com.example.Quan_Ly_Hoc_Sinh_Backend.model.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "lesson_log")
public class LessonLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Mối quan hệ N-1: Lớp học nào
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_id", nullable = false)
    private SchoolClass schoolClass;

    // Mối quan hệ N-1: Giáo viên giảng dạy
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id", nullable = false)
    private Employee teacher;

    // Mối quan hệ N-1: Môn học
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

    @Temporal(TemporalType.DATE)
    @Column(name = "lesson_date", nullable = false)
    private Date lessonDate;

    @Column(name = "period_number", nullable = false)
    private Integer periodNumber; // Tiết học thứ mấy trong ngày

    @Lob // Dùng @Lob cho nội dung lớn (CLOB)
    @Column(name = "lesson_content")
    private String lessonContent; // Nội dung bài giảng

    @Column(name = "notes")
    private String notes; // Nhận xét của giáo viên/giáo vụ

    // Các trường kiểm tra:
    @Column(name = "is_reviewed")
    private Boolean isReviewed = false; // Đã được giáo vụ/quản lý kiểm tra chưa
}
