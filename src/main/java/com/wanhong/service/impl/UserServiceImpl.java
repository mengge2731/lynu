package com.wanhong.service.impl;

import com.wanhong.dao.UserInfoDao;
import com.wanhong.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author wangmeng247
 * @date 2018-02-11 13:49
 */
public class UserServiceImpl implements UserService{
    @Autowired
    UserInfoDao userInfoDao;
}
