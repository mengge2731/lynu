package com.wanhong.controller;

import com.wanhong.domain.UserInfo;
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
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
        return userInfo;
    }
}
