package com.qfedu.controller;

import com.qfedu.entry.Course;
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

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/meet")
@Api(tags = "该类实现所有关于遇见的功能")
public class meetController {
    @Autowired
    CourseService courseService;

    @RequestMapping(value = "/showOneCourse", method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    @ResponseBody
    @ApiOperation(value = "该方法用来展示遇见中的课程")
    public String showOneCourse( Model model,Course course) {
        int id = courseService.getIdByCourseShowImg(course);
        Course course1 = courseService.getCourseById(id);
        model.addAttribute("course", course1);
        return JsonUtils.objectToJson(course);
    }

    @RequestMapping(value = "/showCourseImg", method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    @ResponseBody
    @ApiOperation(value = "该方法用来展示遇见中的图片")
    public String showCourseImg(HttpSession session,Model model, Course course){
        int id = courseService.getIdByCourseShowImg(course);
                return "success";
    }

}
