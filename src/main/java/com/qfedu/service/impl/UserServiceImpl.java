package com.qfedu.service.impl;

import com.qfedu.entry.User;
import com.qfedu.mapper.UserMapper;
import com.qfedu.service.UserService;
import com.qfedu.utils.MailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    public int register(User user) {
        return userMapper.register(user);
    }

    public int validate(User user) {//user 里有email

        //获取六位随机验证码
        String validateCode = MailUtils.getValidateCode(6);
        user.setValidateNum(validateCode);

        //发送验证码
        MailUtils.sendMail(user.getEmail(), "尊敬的客户您好，您正在注册艺伙app，您的验证码是："
                + validateCode + "，请及时输入验证码，欢迎进入艺伙世界！", "艺伙app验证码");
        User _user = userMapper.selectShowUserByEmail(user.getEmail());

        //判断邮箱是否存在，若存在则为忘记密码，修改验证码，若不存在则为注册时添加验证码
        if (_user == null){
            int count = userMapper.insertValidateNum(user);
            return count;
        } else {
            int count = userMapper.updateValidateNum(user);
            return count;
        }

    }

    public boolean isLogin(User user) {
        int result = userMapper.selectUserByUserNameAndPassword(user);
        return result > 0 ? true : false;
    }

    public int selectVerify(String email) {
        return userMapper.selectVerify(email);
    }

    public void updatePasswordByEmail(User user) {
        userMapper.updatePasswordByEmail(user);
    }

    public boolean selectUserByValidateNumAndEmail(User user) {
        int result = userMapper.selectUserByValidateNumAndEmail(user);
        return result > 0 ? true : false;
    }

    public User selectShowUserByEmail(String email) {
        return userMapper.selectShowUserByEmail(email);
    }

    public int updateInformation(User user) {
       return userMapper.updateInformation(user);
    }

    public int updateRealMessage(User user) {
        return userMapper.updateRealMessage(user);
    }

    public int addCourseIdUserId(User user) {
        return userMapper.addCourseIdUserId(user);
    }

    public int selectUserIdByEmail(String email) {
        return userMapper.selectUserIdByEmail(email);
    }

    public User selectUserByTokenId(String tokenId) {
        return userMapper.selectUserByTokenId(tokenId);
    }

    public int updateTokenId(User user) {
        return userMapper.updateTokenId(user);
    }

    public int isRealPassword(User user) {
        return userMapper.isRealPassword(user);
    }

    public void updateNewPassword(User user) {
        userMapper.updateNewPassword(user);
    }

    public int selectUserIdByTokenId(String tokenId) {
        return userMapper.selectUserIdByTokenId(tokenId);
    }
}
