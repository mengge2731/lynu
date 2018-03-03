package com.wanhong.dao;

import com.wanhong.domain.UserInfo;
import com.wanhong.domain.common.UserQuery;

import java.util.List;

/**
 * @author wangmeng247
 * @date 2018-02-11 12:59
 */
public interface UserInfoDao {
    UserInfo getUserInfoById(UserInfo userInfo);
    UserInfo getUserImportantInfoById(UserInfo userInfo);
    //登录校验账号密码
    UserInfo getUserByPhoneAndPassword(UserInfo userInfo);
    //注册的时候用于检查是否存在该用户
    UserInfo getUserInfoByPhone(UserInfo userInfo);
    Integer getUserCount();
    List<UserInfo> getUserByPage(UserQuery userQuery);
    Integer delUserInfoById(UserInfo userInfo);
    Integer updateUserInfo(UserInfo userInfo);
    Integer saveUserInfo(UserInfo userInfo);

}
