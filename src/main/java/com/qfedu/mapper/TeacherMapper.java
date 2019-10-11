package com.qfedu.mapper;

import com.qfedu.entry.Teacher;

import java.util.List;

/**
 * @author 周立雄
 * @date 2019/10/5 14:55
 */
public interface TeacherMapper {
    List<Teacher> selectTeachersByGrade(int teacherGrade);
}
