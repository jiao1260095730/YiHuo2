package com.qfedu.service.impl;

import com.qfedu.entry.Course;
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
}
