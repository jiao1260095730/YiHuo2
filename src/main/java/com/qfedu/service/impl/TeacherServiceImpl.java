package com.qfedu.service.impl;

import com.qfedu.entry.Teacher;
import com.qfedu.mapper.TeacherMapper;
import com.qfedu.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 周立雄
 * @date 2019/10/5 14:54
 */
@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    TeacherMapper teacherMapper;

    public List<Teacher> selectTeachersByGrade(int teacherGrade) {
        return teacherMapper.selectTeachersByGrade(teacherGrade);
    }
}
