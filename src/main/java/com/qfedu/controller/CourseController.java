package com.qfedu.controller;

import com.qfedu.entry.Course;
import com.qfedu.entry.Video;
import com.qfedu.service.CourseService;
import com.qfedu.utils.JsonUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/course")
@Api(tags = "该类实现所有关于course的功能")
public class CourseController {

    @Autowired
    CourseService courseService;

    @ResponseBody
    @RequestMapping(value = "/showList",method = RequestMethod.POST)
    @ApiOperation(value = "该方法用来展示课程列表")
    public String courseList(Model model) {
        List<Course> courseList = courseService.selectAllCourse();
        model.addAttribute("courseList", courseList);
        return JsonUtils.objectToJson(courseList);
    }
}
