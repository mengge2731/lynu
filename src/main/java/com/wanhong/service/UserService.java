package com.wanhong.service;

import com.wanhong.domain.UserInfo;
import com.wanhong.domain.common.Page;
import com.wanhong.domain.param.UserParam;

import java.util.List;

/**
 * @author wangmeng247
 * @date 2018-02-11 13:48
 */
public interface UserService {
    List<UserInfo> getUserInfoList();
    Page<List<UserInfo>> getUserInfoByPage(UserParam userParam);
    Integer delUserByUserId(Long userId);
    Integer updateUserInfo(UserInfo userInfo);
    Integer updateUserPassword(UserInfo userInfo);
}
