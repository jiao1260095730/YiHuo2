package com.qfedu.entry;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Course {
    private Integer id;

    private String courseName;

    private String courseDesc;

    private String buyNum;

    private String meetImg;

    private Integer teacherId;

    private String labelId;

    private Integer typeId;

    private String courseShowImg;

    private List<Course> courseList;
}