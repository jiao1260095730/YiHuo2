package com.qfedu.service;

import com.qfedu.entry.Teacher;
import com.qfedu.entry.User;

import java.util.List;

public interface CommunityService {
    List<Teacher> selectTeacher();

    User selectUser();
}
