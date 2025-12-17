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
@Table(name = "student_card")
public class StudentCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Mối quan hệ 1-1: Mỗi thẻ chỉ thuộc về MỘT Học sinh
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", referencedColumnName = "id", nullable = false, unique = true)
    private Student student;

    @Column(name = "card_number", unique = true, nullable = false, length = 50)
    private String cardNumber; // Mã số trên thẻ

    @Column(name = "issue_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date issueDate; // Ngày phát hành

    @Column(name = "expiry_date")
    @Temporal(TemporalType.DATE)
    private Date expiryDate; // Ngày hết hạn

    @Column(name = "photo_url", length = 255)
    private String photoUrl; // Đường dẫn đến ảnh đại diện học sinh

    @Column(name = "status", length = 30)
    private String status; // Ví dụ: Active, Lost, Expired
}