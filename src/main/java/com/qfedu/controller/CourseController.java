package com.qfedu.controller;

import com.qfedu.entry.Course;
import com.qfedu.entry.Label;
import com.qfedu.entry.Video;
import com.qfedu.service.CourseService;
import com.qfedu.service.LabelService;
import com.qfedu.utils.JsonUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
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
    @Autowired
    LabelService labelService;

    @ResponseBody
    @RequestMapping(value = "/showList", method = RequestMethod.POST)
    @ApiOperation(value = "该方法用来展示课程列表")
    public String courseList(Model model) {
        List<Course> courseList = courseService.selectAllCourse();
        model.addAttribute("courseList", courseList);
        return JsonUtils.objectToJson(courseList);
    }

    @RequestMapping(value = "/showOneCourse", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "该方法用来展示遇见中的课程")
    public String showOneCourse(Integer id, Model model) {
        Course course = courseService.getCourseById(id);
        model.addAttribute("course", course);
        return JsonUtils.objectToJson(course);
    }

    @RequestMapping(value = "/listByLables",method = {RequestMethod.POST,RequestMethod.GET})
    @ApiOperation(value = "点击标签查询")
    @ApiImplicitParam(name = "labelName",value = "点击的标签",required = true,dataType = "String")
    public void listByLabels(String labelName,Model model) {
        //获取标签id
        int labelId = labelService.getIdByName(labelName);
        Label label = new Label();
        label.setId(labelId);
        label.setName(labelName);
        //查询
        List<Course> courseList = courseService.selectCourseListByLabel(label);

        model.addAttribute("courseList",courseList);
    }
}
