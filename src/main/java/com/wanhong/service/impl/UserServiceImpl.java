package com.wanhong.service.impl;

import com.wanhong.dao.UserInfoDao;
import com.wanhong.domain.UserInfo;
import com.wanhong.domain.common.Page;
import com.wanhong.domain.common.UserQuery;
import com.wanhong.domain.param.UserParam;
import com.wanhong.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author wangmeng247
 * @date 2018-02-11 13:49
 */
public class UserServiceImpl implements UserService{

    @Autowired
    UserInfoDao userInfoDao;

    @Override
    public List<UserInfo> getUserInfoList() {
        return null;
    }

    @Override
    public Page<List<UserInfo>> getUserInfoByPage(UserParam userParam) {
        UserQuery userQuery = new UserQuery();
        userQuery.setIndex(userParam.getPageIndex());
        userQuery.setPageSize(userParam.getPageSize());
        List<UserInfo> userInfoList = userInfoDao.getUserByPage(userQuery);
        Integer totalCount = userInfoDao.getUserCount();
        Page page = new Page<>(userInfoList);
        page.setTotalItem(totalCount);
        page.setIndex(userParam.getPageIndex());
        page.setPageSize(userParam.getPageSize());
        return page;
    }
}
