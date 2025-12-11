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
@Table(name = "conduct")
public class Conduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_conduct")
    private int idConduct;

    @Column(name = "code_conduct")
    private String codeConduct;

    @Column(name = "name_conduct", nullable = false)
    private String nameConduct;

    @OneToMany(mappedBy = "conduct", fetch = FetchType.LAZY,
        cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH}
    )
    private List<ReportCard> listReportCards;

}
