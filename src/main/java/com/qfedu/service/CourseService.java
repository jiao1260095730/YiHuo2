package com.qfedu.service;

import com.qfedu.entry.Course;
import com.qfedu.entry.Label;

import java.util.List;

public interface CourseService {

    List<Course> selectAllCourse();

    List<Course> selectCourseListByLabel(Label label);
}
