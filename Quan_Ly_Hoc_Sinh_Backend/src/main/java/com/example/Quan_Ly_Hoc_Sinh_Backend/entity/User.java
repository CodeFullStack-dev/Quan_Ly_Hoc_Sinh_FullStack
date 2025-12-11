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
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private int idUser;

    @Column(name = "username_user", nullable = false, unique = true)
    private String usernameUser;

    @Column(name = "password_user", nullable = false)
    private String passwordUser;

    @Column(name = "enabled_user")
    private boolean enabledUser;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserRole> listUserRoles;

}
