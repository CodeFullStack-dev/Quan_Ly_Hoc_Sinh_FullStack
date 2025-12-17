package com.example.Quan_Ly_Hoc_Sinh_Backend.model.Entity;

import com.example.Quan_Ly_Hoc_Sinh_Backend.model.Enum.ERelationshipType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "student_parent")
public class StudentParent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Khóa ngoại tới Học sinh
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    // Khóa ngoại tới Phụ huynh
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id", nullable = false)
    private Parent parent;

    // Vai trò của phụ huynh đối với học sinh (Cha, Mẹ, Người giám hộ)
    @Enumerated(EnumType.STRING)
    @Column(name = "relationship_type", nullable = false)
    private ERelationshipType relationshipType;

    @Column(name = "is_primary_contact")
    private Boolean isPrimaryContact = false; // Phụ huynh liên hệ chính

    // Đảm bảo không có hai bản ghi giống hệt nhau (cho cặp student_id và parent_id)
    // @UniqueConstraint(columnNames = {"student_id", "parent_id"})
    // Thêm constraint này trong @Table của Entity nếu cần
}
