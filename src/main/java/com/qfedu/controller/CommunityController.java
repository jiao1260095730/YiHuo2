package com.qfedu.controller;


import com.qfedu.entry.Teacher;
import com.qfedu.entry.User;
import com.qfedu.service.CommunityService;
import com.qfedu.utils.JsonUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/community")
@Api(tags = "该类实现所有关于community的功能")
public class CommunityController {

    @Autowired
    CommunityService communityService;

    @RequestMapping(value = "/focusCommunity", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    @ApiOperation(value = "社区关注老师信息展示")
    public String focusCommunity() {
       List<Teacher> teacherList  = communityService.selectTeacher();
        return JsonUtils.objectToJson(teacherList);
    }

    @RequestMapping(value = "/showOneUser", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    @ApiOperation(value = "社区随机展示一个用户")
    public String showOneUser() {
        User user = communityService.selectUser();
        return JsonUtils.objectToJson(user);
    }

}
