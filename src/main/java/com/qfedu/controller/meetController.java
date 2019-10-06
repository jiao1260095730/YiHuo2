package com.qfedu.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qfedu.entry.Course;

import com.qfedu.service.CourseService;
import com.qfedu.utils.JsonUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/meet")
@Api(tags = "该类实现所有关于遇见的功能")
public class meetController {
    @Autowired
    CourseService courseService;


    //展示遇见图片 进行分页
    @RequestMapping(value = "/showCourseListImg", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    @ApiOperation(value = "该方法用来分页展示课程列表")


    public String showCourseListImg(Model model, @RequestParam(name = "当前页页码",required = false, defaultValue = "1") int page, @RequestParam(name = "每页显示个数",required = false, defaultValue = "5") int pageSize) {
        PageHelper.startPage(page, 1);
        List<Course> courseList = courseService.courseListAll(page, pageSize);
        PageInfo<Course> pageInfo = new PageInfo<Course>(courseList);
        model.addAttribute("pageInfo", pageInfo);
        return JsonUtils.objectToJson(courseList);

    }


   //@RequestMapping(value = "/showOneCourse", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
   //@ResponseBody
   //@ApiOperation(value = "该方法用来展示遇见中的课程")
   //@ApiImplicitParam(name = "id", value = "想要查找的id", required = true, dataType = "Integer")

   //public String showOneCourse(Integer id) {
   //    Course course = courseService.getCourseById(id);

   //    return JsonUtils.objectToJson(course);
   //}

   //@RequestMapping(value = "/showCourseImg", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
   //@ResponseBody
   //@ApiOperation(value = "该方法用来展示遇见中的图片")
   //@ApiImplicitParam(name = "id", value = "想要查找的id", required = true, dataType = "Integer")
   //public String showCourseImg(HttpSession session, Course course) {
   //    int id = courseService.getIdByCourseShowImg(course);
   //    return "success";
   //}

}
