package com.example.Quan_Ly_Hoc_Sinh_Backend.dto.ClassroomDTOs;

import lombok.Data;

@Data
public class ClassroomRequest {
    private String nameClassroom;
    private String gradeLevelClassroom;
    private String academicYearClassroom;

    private Integer schoolId; // ID trường

    private Integer homeroomTeacherId; // ID giáo viên chủ nhiệm
}
