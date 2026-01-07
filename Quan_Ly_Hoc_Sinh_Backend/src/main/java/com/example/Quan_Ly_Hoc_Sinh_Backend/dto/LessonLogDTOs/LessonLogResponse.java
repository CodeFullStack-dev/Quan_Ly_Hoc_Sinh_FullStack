package com.example.Quan_Ly_Hoc_Sinh_Backend.dto.LessonLogDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LessonLogResponse {
    private Long id;
    private String ClassName;
    private String teacherName;
    private String subjectName;
    private Date lessonDate;
    private Integer periodNumber;
    private String lessonContent;
    private String notes;
}
