package com.qfedu.service;

import com.qfedu.entry.User;

public interface UserService {

    int register(User user);

    int validate(String email);

    boolean isLogin(User user);

    int selectVerify(String email);

    void updatePasswordByEmail(User user);

    boolean selectUserByValidateNumAndEmail(User user);

    User selectShowUserByEmail(String email);

    int updateInformation(User user);

    int updateRealMessage(User user);

    int addCourseIdUserId(User user);

    int selectUserIdByEmail(String email);

    User selectUserByTokenId(String tokenId);

    int updateTokenId(User user);
}
