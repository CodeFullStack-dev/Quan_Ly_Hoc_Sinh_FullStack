package com.example.Quan_Ly_Hoc_Sinh_Backend.model.Entity;

import com.example.Quan_Ly_Hoc_Sinh_Backend.model.Enum.EGender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "student_code", unique = true, nullable = false, length = 20)
    private String studentCode;

    @Column(name = "full_name", nullable = false, length = 100)
    private String fullName;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    // Giới tính (sử dụng Enum)
    @Enumerated(EnumType.STRING)
    @Column(name = "gender", length = 10)
    private EGender gender;

    @Column(name = "current_address", length = 255)
    private String currentAddress;

    // Mối quan hệ N-1: Một học sinh thuộc một lớp
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_id", nullable = false)
    private SchoolClass schoolClass;

    // Lưu ý: Mối quan hệ với PHU_HUYNH sẽ được thiết lập thông qua bảng trung gian (StudentParent).
    // Mối quan hệ với Bảng điểm/Học bạ sẽ được thiết lập trong các Entity tương ứng.
    // Mối quan hệ 1-N (Học sinh - Phụ huynh): Được quản lý bởi Entity trung gian StudentParent
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<StudentParent> parentLinks;
}
