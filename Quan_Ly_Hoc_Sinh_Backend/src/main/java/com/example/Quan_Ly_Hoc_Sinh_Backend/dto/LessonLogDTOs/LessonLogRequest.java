package com.example.Quan_Ly_Hoc_Sinh_Backend.dto.LessonLogDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LessonLogRequest {
    private Long classId;
    private Long teacherId;
    private Long subjectId;
    private Date lessonDate;
    private Integer periodNumber;
    private String lessonContent;
    private String notes;
}
