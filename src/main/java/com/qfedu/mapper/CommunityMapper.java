package com.qfedu.mapper;

import com.qfedu.entry.Teacher;
import com.qfedu.entry.User;

import java.util.List;

public interface CommunityMapper {

    List<Teacher> selectTeacher();

    User selectUser();
}
