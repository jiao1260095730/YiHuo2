package com.qfedu.controller;


import com.qfedu.entry.ResultInfo;
import com.qfedu.entry.Teacher;
import com.qfedu.entry.User;
import com.qfedu.service.CommunityService;
import com.qfedu.utils.JsonUtils;
import com.qfedu.utils.PutImg;
import com.qfedu.utils.QiniuCloudUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

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

    /**
     * 图片上传方法展示(例子)
     * 调用工具类 PutImg putImg = new PutImg();
     * 中的putImg.uploadImg(image);
     * 返回值为图片服务器的url 类型为String
     * 如果参数为空url为 400,文件为空，请重新上传
     * 参数异常 "500,文件上传发生异常！"
     * 成功为 图片服务器的url
     * @param image
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/uploadImg", method=RequestMethod.POST)
    public String ttuploadImg(@RequestParam MultipartFile image) {
        PutImg putImg = new PutImg();
        String url = putImg.uploadImg(image);
        System.out.println(url);
        return url;
    }
}
