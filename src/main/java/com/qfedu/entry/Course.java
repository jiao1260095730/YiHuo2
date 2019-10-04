package com.qfedu.entry;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Course {
    private Integer id;

    private String courseName;

    private String courseDesc;

    private String buyNum;

    private Integer teacherId;

    private String labelId;

    private Integer typeId;
}