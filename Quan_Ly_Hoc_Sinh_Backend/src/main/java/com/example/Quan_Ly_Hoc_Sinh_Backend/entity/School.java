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
@Table(name = "school")
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_school")
    private int idSchool;

    @Column(name = "name_school", nullable = false)
    private String nameSchool;

    @Column(name = "address_school")
    private String addressSchool;

    @Column(name = "phone_school")
    private String phoneSchool;

    @Column(name = "email_school")
    private String emailSchool;

    @OneToMany(mappedBy = "school", fetch = FetchType.LAZY,
                    cascade = { CascadeType.DETACH, CascadeType.MERGE,
                    CascadeType.PERSIST, CascadeType.REFRESH
    })
    private List<Classroom> listClassrooms;

    @OneToMany(mappedBy = "school", fetch = FetchType.LAZY,
            cascade = { CascadeType.DETACH, CascadeType.MERGE,
                    CascadeType.PERSIST, CascadeType.REFRESH
    })
    private List<Staff> listStaffs;

    @OneToMany(mappedBy = "school", fetch = FetchType.LAZY,
            cascade = { CascadeType.DETACH, CascadeType.MERGE,
                    CascadeType.PERSIST, CascadeType.REFRESH
    })
    private List<Student> listStudents;
}
