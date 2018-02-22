package com.wanhong.dao;

import com.wanhong.domain.UserInfo;
import com.wanhong.domain.common.UserQuery;

import java.util.List;

/**
 * @author wangmeng247
 * @date 2018-02-11 12:59
 */
public interface UserInfoDao {
    List<UserInfo> getUserInfoList();
    UserInfo getUserInfoById(UserInfo userInfo);
    Integer getUserCount();
    List<UserInfo> getUserByPage(UserQuery userQuery);
}
