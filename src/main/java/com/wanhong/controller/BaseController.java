package com.wanhong.controller;

import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
import com.wanhong.domain.UserInfo;
import com.wanhong.domain.vo.UserInfoVo;
import com.wanhong.util.BeanUtil;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wangmeng247
 * @date 2018-02-10 11:11
 */
public class BaseController {

    UserInfo getMyInfo(){
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes)ra).getRequest();
        UserInfoVo userInfoVo = (UserInfoVo) request.getSession().getAttribute("userInfo");
        UserInfo userInfo = new UserInfo();
        BeanUtil.copyProperties(userInfoVo,userInfo);
        return userInfo;
    }
}
