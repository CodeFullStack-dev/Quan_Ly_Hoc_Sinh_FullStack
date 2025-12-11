package com.example.Quan_Ly_Hoc_Sinh_Backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "parent")
public class Parent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_parent")
    private int idParent;

    @Column(name = "full_name_parent")
    private String fullNameParent;

    @Column(name = "phone_parent")
    private String phoneParent;

    @Column(name = "email_parent")
    private String emailParent;

    @Column(name = "address_parent")
    private String addressParent;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id_parent")
    private User user;

    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<StudentParent> listStudentParents;
}
