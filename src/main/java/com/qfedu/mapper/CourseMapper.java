package com.qfedu.mapper;

import com.qfedu.entry.Course;
import com.qfedu.entry.Label;

import java.util.List;

public interface CourseMapper {
    List<Course> selectAllCourse();

    Course getCourseById(String id);

    List<Course> selectCourseListByLabel(Label label);

    List<Course> selectCourseListByqueryText(String queryText);

    int getIdByCourseShowImg(Course course);

    List<Course> selectAllCourseOrderByIdDesc();




}
