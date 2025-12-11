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
@Table(name = "score_type")
public class ScoreType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_score_type")
    private int idScoreType;

    @Column(name = "name_score_type", nullable = false, length = 100, unique = true)
    private String nameScoreType;

    @Column(name = "weight_score_type", nullable = false)
    private double weightScoreType;

    @OneToMany(mappedBy = "scoreType", fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}
    )
    private List<ScoreRecord> listScoreRecords;
}
