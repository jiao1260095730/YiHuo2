package com.qfedu.service;

import com.qfedu.entry.Teacher;

import java.util.List;

/**
 * @author 周立雄
 * @date 2019/10/5 14:53
 */
public interface TeacherService {
    List<Teacher> selectTeachersByGrade(String teacherGrade);
}
