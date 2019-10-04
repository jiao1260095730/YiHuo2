package com.qfedu.service.impl;

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

    public Course getCourseById(Integer id) {
        return courseMapper.getCourseById(id);
    }

    public List<Course> selectCourseListByLabel(Label label) {
        return courseMapper.selectCourseListByLabel(label);
    }
}
