package com.qfedu.mapper;

import com.qfedu.entry.Course;
import com.qfedu.entry.Label;

import java.util.List;

public interface CourseMapper {
    List<Course> selectAllCourse();

    List<Course> selectCourseListByLabel(Label label);
}
