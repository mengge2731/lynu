package com.wanhong.controller;


import com.wanhong.common.errorcode.BusinessCode;
import com.wanhong.domain.ResultJson;
import com.wanhong.domain.UserInfo;
import com.wanhong.domain.common.Page;
import com.wanhong.domain.param.UserParam;
import com.wanhong.service.UserService;
import com.wanhong.util.BeanUtil;
import com.wanhong.util.BusinessBodyConvertUtil;
import com.wanhong.util.FastjsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 请填写类的描述
 *
 * @author wangmeng36
 * @date 2017-11-14 14:55
 */
@Controller
@RequestMapping("/function/user")
public class UserController {
    //添加一个日志器
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping("/getPageData")
    @ResponseBody
    public ResultJson<Page<List<UserInfo>>> getPageData(String body){
        UserParam userParam = BusinessBodyConvertUtil.buildBusinessParam(body,UserParam.class);
        Page<List<UserInfo>> userInfoPage =userService.getUserInfoByPage(userParam);
        userInfoPage.setIndex(userParam.getIndex());
        userInfoPage.setPageSize(userParam.getPageSize());
        ResultJson<Page<List<UserInfo>>> resultJson = new ResultJson<>(BusinessCode.SUCCESS,userInfoPage);
        logger.info("resultJson:{}", FastjsonUtil.objectToJson(resultJson));
        return resultJson;
    }
}
