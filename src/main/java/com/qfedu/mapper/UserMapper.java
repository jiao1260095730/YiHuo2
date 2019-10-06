package com.qfedu.mapper;

import com.qfedu.entry.User;

public interface UserMapper {

    int register(User user);

    int insertValidateNum(User user);

    int selectUserByUserNameAndPassword(User user);

    int selectVerify(String email);

    void updatePasswordByEmail(User user);

    int selectUserByValidateNumAndEmail(User user);

    User selectShowUserByEmail(String email);

    int updateInformation(User user);

    int updateRealMessage(User user);

    int addCourseIdUserId(User user);

    int selectUserIdByEmail(String email);

    User selectUserByTokenId(String tokenId);

    int updateTokenId(User user);

    int updateValidateNum(User user);

    int isRealPassword(User user);

    void updateNewPassword(User user);
}
