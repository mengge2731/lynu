package com.wanhong.controller;


import com.sun.org.apache.bcel.internal.generic.NEW;
import com.wanhong.common.errorcode.BusinessCode;
import com.wanhong.domain.ResultJson;
import com.wanhong.domain.UserInfo;
import com.wanhong.domain.common.Page;
import com.wanhong.domain.param.UserParam;
import com.wanhong.domain.vo.UserInfoVo;
import com.wanhong.service.UserService;
import com.wanhong.util.BeanUtil;
import com.wanhong.util.BusinessBodyConvertUtil;
import com.wanhong.util.FastjsonUtil;
import com.wanhong.util.StringUtil;
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
public class UserController extends BaseController {
    //添加一个日志器
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping("/getUserByPage")
    @ResponseBody
    public ResultJson<Page<List<UserInfoVo>>> getUserByPage(String body){
        UserParam userParam = BusinessBodyConvertUtil.buildBusinessParam(body,UserParam.class);
        Page<List<UserInfoVo>> userInfoVoPage =userService.getUserInfoByPage(userParam);
        ResultJson<Page<List<UserInfoVo>>> resultJson = new ResultJson<>(BusinessCode.SUCCESS,userInfoVoPage);
        logger.info("resultJson:{}", FastjsonUtil.objectToJson(resultJson));
        return resultJson;
    }

    @RequestMapping("/getUserInfo")
    @ResponseBody
    public ResultJson<UserInfoVo> getUserInfo(){
        UserInfoVo userInfoVo = new UserInfoVo();
        UserInfo userInfo = userService.getUserInfoByPhone(this.getMyInfo());
        BeanUtil.copyProperties(userInfo,userInfoVo);
        ResultJson<UserInfoVo> resultJson = new ResultJson<>(BusinessCode.SUCCESS, userInfoVo);
        logger.info("resultJson:{}", FastjsonUtil.objectToJson(resultJson));
        return resultJson;
    }

    @RequestMapping("/updateUserInfo")
    @ResponseBody
    public ResultJson<Boolean> updateUserInfo(String body){
        UserParam userParam = BusinessBodyConvertUtil.buildBusinessParam(body,UserParam.class);
        UserInfo userInfo = new UserInfo();
        BeanUtil.copyProperties(userParam,userInfo);
        if (StringUtil.hasBlank(userInfo.getUserName(),userInfo.getDesc())){
            return new ResultJson<>(BusinessCode.ILLEGAL_ARG_ERROR,false);
        }
        ResultJson<Boolean> resultJson = new ResultJson<>(BusinessCode.UPDATE_ERROR, false);
        if (userService.updateUserInfo(userInfo)>0){
            resultJson =  new ResultJson<>(BusinessCode.SUCCESS,true);
        }
        logger.info("resultJson:{}", FastjsonUtil.objectToJson(resultJson));
        return resultJson;
    }
}
