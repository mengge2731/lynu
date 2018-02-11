package com.wanhong.service;

import com.wanhong.domain.UserInfo;
import com.wanhong.domain.common.Page;

import java.util.List;

/**
 * @author wangmeng247
 * @date 2018-02-11 13:48
 */
public interface UserService {
    List<UserInfo> getUserInfoList();
    Page<List<UserInfo>> getUserInfoByPage(Integer index);
}
