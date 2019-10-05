package com.qfedu.service.impl;

import com.qfedu.entry.Teacher;
import com.qfedu.entry.User;
import com.qfedu.mapper.CommunityMapper;
import com.qfedu.service.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CommunityServiceImpl implements CommunityService {
    @Autowired
    CommunityMapper communityMapper;

    public List<Teacher> selectTeacher() {
        return communityMapper.selectTeacher();
    }

    public User selectUser() {
        return communityMapper.selectUser();
    }
}
