package com.qfedu.service.impl;

import com.github.pagehelper.PageHelper;
import com.qfedu.entry.Course;
import com.qfedu.entry.Label;
import com.qfedu.mapper.CourseMapper;
import com.qfedu.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    CourseMapper courseMapper;


    public List<Course> selectAllCourse() {
        return courseMapper.selectAllCourse();
    }

    public Course getCourseById(String id) {
        return courseMapper.getCourseById(id);
    }

    public List<Course> selectCourseListByLabel(Label label) {
        return courseMapper.selectCourseListByLabel(label);
    }

    public List<Course> selectCourseListByqueryText(String queryText) {
        return courseMapper.selectCourseListByqueryText(queryText);
    }

    public int getIdByCourseShowImg(Course course) {
        return courseMapper.getIdByCourseShowImg(course);
    }

    public List<Course> selectGuessLikeCourseList() {
        return courseMapper.selectAllCourseOrderByIdDesc();
    }


    public List<Course> courseListAll(int page, int pageSize) {
        PageHelper.startPage(1, 5);
        List<Course> courseList = courseMapper.selectAllCourse();
        return courseList;
    }
}
