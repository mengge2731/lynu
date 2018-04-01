package com.wanhong.service.impl;

import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
import com.wanhong.dao.UserInfoDao;
import com.wanhong.domain.UserInfo;
import com.wanhong.domain.common.Page;
import com.wanhong.domain.common.UserQuery;
import com.wanhong.domain.param.UserParam;
import com.wanhong.domain.vo.UserInfoVo;
import com.wanhong.service.UserService;
import com.wanhong.util.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
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
    public Page<List<UserInfoVo>> getUserInfoByPage(UserParam userParam) {
        UserQuery userQuery = new UserQuery();
        userQuery.setIndex(userParam.getIndex());
        userQuery.setPageSize(userParam.getPageSize());
        List<UserInfo> userInfoList = userInfoDao.getUserByPage(userQuery);
        List<UserInfoVo> userInfoVoList = new ArrayList<>();
        if (userInfoList != null && userInfoList.size()>0){
            for (UserInfo userInfo:userInfoList){
                UserInfoVo userInfoVo = new UserInfoVo();
                BeanUtil.copyProperties(userInfo,userInfoVo);
                userInfoVoList.add(userInfoVo);
            }
        }
        Integer totalCount = userInfoDao.getUserCount();
        Page page = new Page<>(userInfoVoList);
        page.setTotalItem(totalCount);
        page.setIndex(userParam.getIndex());
        page.setPageSize(userParam.getPageSize());
        return page;
    }

    @Override
    public Integer delUserByUserId(Long userId) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(userId);
        return userInfoDao.delUserInfoById(userInfo);
    }

    @Override
    public UserInfo getUserInfoById(UserInfo userInfo) {
        return null;
    }

    @Override
    public Integer updateUserInfo(UserInfo userInfo) {
        return userInfoDao.updateUserInfo(userInfo);
    }

    @Override
    public Integer updateUserPassword(UserInfo userInfo) {
        return userInfoDao.updateUserPassword(userInfo);
    }

    @Override
    public UserInfo getUserInfoByPhone(UserInfo userInfo) {
        return userInfoDao.getUserInfoByPhone(userInfo);
    }

    @Override
    public UserInfo saveUserInfo(UserInfo userInfo) {
        Integer res =  userInfoDao.saveUserInfo(userInfo);
        if (res>0){
            return userInfo;
        }else{
            return null;
        }
    }

    @Override
    public UserInfo getUserByPhoneAndPassword(UserInfo userInfo) {
        return userInfoDao.getUserByPhoneAndPassword(userInfo);
    }

    @Override
    public UserInfo getUserByPhoneAndPasswordAndMsg(UserInfo userInfo) {
        return userInfoDao.getUserByPhoneAndPasswordAndMsg(userInfo);
    }

    @Override
    public Integer updateUserStatus(UserInfo userInfo) {
        return userInfoDao.updateUserStatus(userInfo);
    }

    @Override
    public Integer updateUserType(UserInfo userInfo) {
        return userInfoDao.updateUserType(userInfo);
    }

    @Override
    public UserInfo getUserByPhoneAndPasswordWithOutStatus(UserInfo userInfo) {
        return userInfoDao.getUserByPhoneAndPasswordWithOutStatus(userInfo);
    }
}
