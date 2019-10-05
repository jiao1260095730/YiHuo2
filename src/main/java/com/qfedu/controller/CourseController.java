package com.qfedu.controller;

import com.qfedu.entry.Course;
import com.qfedu.entry.Label;
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
@Api(tags = "该类实现所有关于course的功能（主页的功能也在此类实现）")
public class CourseController {

    @Autowired
    CourseService courseService;
    @Autowired
    LabelService labelService;


    @RequestMapping(value = "/showList", method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    @ResponseBody
    @ApiOperation(value = "该方法用来展示课程列表")
    public String courseList(Model model) {
        List<Course> courseList = courseService.selectAllCourse();

        return JsonUtils.objectToJson(courseList);
    }



    @RequestMapping(value = "/listByLabels",method = {RequestMethod.POST,RequestMethod.GET},produces = "application/json;charset=UTF-8")
    @ResponseBody
    @ApiOperation(value = "点击标签查询（前端传来标签的name）")
    @ApiImplicitParam(name = "labelName",value = "点击的标签",required = true,dataType = "String")
    public String listByLabels(String labelName) {
        //获取标签id
        int labelId = labelService.getIdByName(labelName);
        Label label = new Label();
        label.setId(labelId);
        label.setName(labelName);
        //查询
        List<Course> courseList = courseService.selectCourseListByLabel(label);

        return JsonUtils.objectToJson(courseList);
    }

    /**
     * 搜索栏输入文字，模糊查询所有课程（1.courseName 2.courseDesc）
     */
    @RequestMapping(value = "/queryCourse",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    @ResponseBody
    @ApiOperation(value = "输入文字查询课程")
    @ApiImplicitParam(name = "queryText",value = "输入的文字",required = true,dataType = "String")
    public String queryCourse(String queryText) {
        List<Course> courseList = courseService.selectCourseListByqueryText(queryText);
        return JsonUtils.objectToJson(courseList);
    }
}
