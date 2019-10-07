package com.qfedu.controller;

import com.qfedu.entry.Data;
import com.qfedu.entry.User;
import com.qfedu.service.UserService;
import com.qfedu.utils.JsonUtils;
import com.qfedu.utils.UUIDUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
@Api(tags = "该类实现所有关于user的功能")
public class UserController {

    @Autowired
    UserService userService;


    /**
     * 该方法用于验证邮箱是否已经被注册
     *
     * @param user 从页面获取到的user, 包含email
     * @return 以被注册返回 fail，表示该email不能使用，否则返回 success 表示该email可以使用
     */
    @RequestMapping(value = "/verify", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    @ApiOperation(value = "该方法用来验证邮箱是否存在")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "email", value = "邮箱", required = true, dataType = "String")
    })
    public String verify(@RequestBody User user) {
        int count = userService.selectVerify(user.getEmail());
        Data data = new Data();

        if (count > 0) {
            data.setCode(400);
            data.setMsg("邮箱已存在");
            return JsonUtils.objectToJson(data);
        }
        data.setCode(200);
        data.setMsg("邮箱可以使用");
        return JsonUtils.objectToJson(data);
    }

    @ApiOperation(value = "该方法用于用户使用邮箱注册，输入邮箱，密码，验证码")
    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "email", value = "邮箱", required = true, dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String"),
            @ApiImplicitParam(name = "validateNum", value = "验证码", required = true, dataType = "String")
    })
    public String register(@RequestBody User user) {
        System.out.println(user);
        int count = userService.register(user);
        Data data = new Data();

        if (count > 0) {
            data.setCode(200);
            data.setMsg("注册成功");
            return JsonUtils.objectToJson(data);
        }
        data.setCode(400);
        data.setMsg("注册失败");
        return JsonUtils.objectToJson(data);
    }

    @RequestMapping(value = "/isLogin", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    @ApiOperation(value = "验证用户名email和密码password登陆")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "email", value = "邮箱", required = true, dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String")
    })
    @CrossOrigin(value = "*" , allowedHeaders = "*")
    public String isLogin(@RequestBody User user) {

        String tokenId = UUIDUtils.getUUID();
        user.setTokenId(tokenId);

        boolean result = userService.isLogin(user);
        Data data = new Data();

        if (result) {

            data.setCode(200);
            data.setToken(tokenId);
            data.setMsg("登录成功");

            userService.updateTokenId(user);
            return JsonUtils.objectToJson(data);
        } else {
            data.setCode(400);
            data.setMsg("登录失败");
            return JsonUtils.objectToJson(data);
        }
    }

    @RequestMapping(value = "/validate", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    @ApiOperation(value = "用户注册时和忘记密码时发送验证码并保存至数据库")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "email", value = "邮箱", required = true, dataType = "String")
    })
    @CrossOrigin(value = "*", allowedHeaders = "*")
    public String validate(@RequestBody User user) {
        int count = userService.validate(user);
        Data data = new Data();
        if (count > 0) {
            data.setCode(200);
            data.setMsg("已发送验证码");
            return JsonUtils.objectToJson(data);
        }
        data.setCode(400);
        data.setMsg("发送验证码失败");
        return JsonUtils.objectToJson(data);
    }


    @RequestMapping(value = "/resetPassword", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    @ApiOperation(value = "重置密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "email", value = "邮箱", required = true, dataType = "String"),
            @ApiImplicitParam(name = "password", value = "新密码", required = true, dataType = "String"),
            @ApiImplicitParam(name = "validateNum", value = "验证码", required = true, dataType = "String")
    })
    public String resetPassword(@RequestBody User user) {

        boolean result = userService.selectUserByValidateNumAndEmail(user);
        Data data = new Data();

        if (!result) {
            data.setCode(400);
            data.setMsg("验证码不正确");
            return JsonUtils.objectToJson(data);
        }

        userService.updatePasswordByEmail(user);
        data.setCode(200);
        data.setMsg("修改密码成功");

        return JsonUtils.objectToJson(data);
    }

    @RequestMapping(value = "/setNewPassword", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    @ApiOperation(value = "验证旧密码是否正确，修改密码，设置新密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tokenId", value = "token值", required = true, dataType = "String"),
            @ApiImplicitParam(name = "password", value = "旧密码", required = true, dataType = "String"),
            @ApiImplicitParam(name = "newPassword", value = "新密码", required = true, dataType = "String")
    })
    public String setNewPassword(@RequestBody User user) {
        Data data = new Data();
        int login = userService.isRealPassword(user);

        //如果login为0， 则旧密码不正确，不可以设置新密码
        if (login == 0) {
            data.setCode(400);
            data.setMsg("旧密码错误，修改密码失败");
            return JsonUtils.objectToJson(data);
        }

        userService.updateNewPassword(user);

        data.setCode(200);
        data.setMsg("修改密码成功");
        return JsonUtils.objectToJson(data);
    }

    /**
     * 完善个人资料
     *
     * @param user    实体User
     * @return 成功返回success ，失败返回 fail
     */
    @RequestMapping(value = "/updateInformation", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    @ApiOperation(value = "根据token值在设置中完善个人资料")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tokenId", value = "token值", required = true, dataType = "String"),
            @ApiImplicitParam(name = "headImgUrl", value = "头像", required = true, dataType = "String"),
            @ApiImplicitParam(name = "userName", value = "昵称", required = true, dataType = "String"),
            @ApiImplicitParam(name = "gender", value = "性别", required = true, dataType = "String"),
            @ApiImplicitParam(name = "birthday", value = "生日", required = true, dataType = "String"),
            @ApiImplicitParam(name = "address", value = "地区", required = true, dataType = "String"),
            @ApiImplicitParam(name = "profession", value = "职业", required = true, dataType = "String"),
            @ApiImplicitParam(name = "trade", value = "行业", required = true, dataType = "String"),
            @ApiImplicitParam(name = "education", value = "学历", required = true, dataType = "String")
    })
    public String updateInformation(@RequestBody User user) {
        int count = userService.updateInformation(user);
        Data data = new Data();
        if (count > 0) {
            data.setCode(200);
            data.setMsg("修改成功");
            return JsonUtils.objectToJson(data);
        }
        data.setCode(400);
        data.setMsg("修改失败");
        return JsonUtils.objectToJson(data);
    }

    @RequestMapping(value = "/showUser", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    @ApiOperation(value = "根据用户的email展示用户的所有信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tokenId", value = "token值", required = true, dataType = "String")
    })
    public String showUser(@RequestBody User user) {
        //String email = (String) session.getAttribute("email");
        //User user = userService.selectShowUserByEmail(email);
        //model.addAttribute("user", user);
        Data data = new Data();
        if (user.getTokenId() != null && !user.getTokenId().equals("")) {
            User _user = userService.selectUserByTokenId(user.getTokenId());

            return JsonUtils.objectToJson(_user);
        }
        data.setCode(400);
        data.setMsg("获取用户信息失败");
        return JsonUtils.objectToJson(data);
    }

    /**
     * 退出登录
     *
     * @param session 从页面的得到的邮箱账号
     * @return 返回登录初始页面，目前测试为success；
     */
    @RequestMapping(value = "/exit", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    @ApiOperation(value = "退出登录")
    public String login(HttpSession session) {
        session.removeAttribute("email");
        return "success";
    }

    /**
     * 修改个人信息的真实资料
     *
     * @param user    实体类user
     * @return 成功返回 success ，失败返回 fail
     */
    @RequestMapping(value = "/updateRealMessage", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    @ApiOperation(value = "根据token值修改个人信息的真实资料")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tokenId", value = "token值", required = true, dataType = "String"),
            @ApiImplicitParam(name = "realName", value = "真实姓名", required = true, dataType = "String"),
            @ApiImplicitParam(name = "weiXin", value = "微信", required = true, dataType = "String"),
            @ApiImplicitParam(name = "schoolTag", value = "毕业学校", required = true, dataType = "String"),
            @ApiImplicitParam(name = "major", value = "专业", required = true, dataType = "String"),
            @ApiImplicitParam(name = "beGoodAt", value = "擅长领域", required = true, dataType = "String"),
            @ApiImplicitParam(name = "experience", value = "经历", required = true, dataType = "String")
    })
    public String updateRealMessage(@RequestBody User user) {

        int count = userService.updateRealMessage(user);
        Data data = new Data();
        if (count > 0) {
            data.setCode(200);
            data.setMsg("修改成功");
            return JsonUtils.objectToJson(data);
        }
        data.setCode(400);
        data.setMsg("修改失败");
        return JsonUtils.objectToJson(data);
    }

    @RequestMapping(value = "/userCollect", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    @ApiOperation(value = "用来收藏课程")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tokenId", value = "token值", required = true, dataType = "String"),
            @ApiImplicitParam(name = "courseId", value = "课程ID", required = true, dataType = "String")
    })
    public String userCollect(@RequestBody User user) {
        String tokenId = user.getTokenId();
        int userId = userService.selectUserIdByTokenId(tokenId);
        user.setId(userId);
        int count = userService.addCourseIdUserId(user);
        Data data = new Data();

        if (count > 0) {
            data.setCode(200);
            data.setMsg("收藏成功");
            return JsonUtils.objectToJson(data);
        }
        data.setCode(400);
        data.setMsg("收藏失败");
        return JsonUtils.objectToJson(data);
    }
}