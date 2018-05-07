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
        ResultJson<UserInfoVo> resultJson = null;
        try{
            UserInfo userInfo = userService.getUserInfoByPhone(this.getMyInfo());
            BeanUtil.copyProperties(userInfo,userInfoVo);
            resultJson = new ResultJson<>(BusinessCode.SUCCESS, userInfoVo);
            logger.info("resultJson:{}", FastjsonUtil.objectToJson(resultJson));
        }catch (Exception e){
            e.printStackTrace();
        }

        return resultJson;
    }

    @RequestMapping("/updateUserInfo")
    @ResponseBody
    public ResultJson<Boolean> updateUserInfo(String body){
        ResultJson<Boolean> resultJson = new ResultJson<>(BusinessCode.UPDATE_ERROR, false);
        try{
            logger.info("UserController--updateUserInfo body:{}", body);
            UserParam userParam = BusinessBodyConvertUtil.buildBusinessParam(body,UserParam.class);
            UserInfo userInfo = this.getMyInfo();
            userInfo.setUserDesc(userParam.getDesc());
            userInfo.setUserName(userParam.getUserName());
            if (StringUtil.hasBlank(userInfo.getUserName(),userInfo.getUserDesc())){
                return new ResultJson<>(BusinessCode.ILLEGAL_ARG_ERROR,false);
            }

            if (userService.updateUserInfo(userInfo)>0){
                resultJson =  new ResultJson<>(BusinessCode.SUCCESS,true);
            }
            logger.info("resultJson:{}", FastjsonUtil.objectToJson(resultJson));

        }catch (Exception e){
            e.printStackTrace();
        }
        return resultJson;
    }

    @RequestMapping("/changePassword")
    @ResponseBody
    public ResultJson<Boolean> changePassword(String body){
        UserParam userParam = BusinessBodyConvertUtil.buildBusinessParam(body,UserParam.class);
        UserInfo userInfo = this.getMyInfo();
        userInfo.setPassword(userParam.getOldPassword());
        if (StringUtil.hasBlank(userInfo.getPassword(),userInfo.getPhone(),userParam.getNewPassword())
                || userInfo.getUserId() ==null || userInfo.getUserId()<=0){
            return new ResultJson<>(BusinessCode.ILLEGAL_ARG_ERROR,false);
        }
        UserInfo oldUserInfo = userService.getUserByPhoneAndPassword(userInfo);
        if (oldUserInfo == null){
            return new ResultJson<>(BusinessCode.UPDATE_ERROR,false);
        }

        userInfo.setPassword(userParam.getNewPassword());
        ResultJson<Boolean> resultJson = new ResultJson<>(BusinessCode.UPDATE_ERROR, false);
        if (userService.updateUserPassword(userInfo)>0){
            resultJson =  new ResultJson<>(BusinessCode.SUCCESS,true);
        }
        logger.info("resultJson:{}", FastjsonUtil.objectToJson(resultJson));
        return resultJson;
    }

    @RequestMapping("/updateUserStatus")
    @ResponseBody
    public ResultJson<Boolean> updateUserStatus(String body){
        ResultJson<Boolean> resultJson = new ResultJson<>(BusinessCode.UPDATE_ERROR, false);
        try{
            logger.info("UserController--updateUserStatus body:{}", body);
            UserParam userParam = BusinessBodyConvertUtil.buildBusinessParam(body,UserParam.class);
            UserInfo userInfo = this.getMyInfo();
            if (!"2".equals(userInfo.getUserType())){
                resultJson = new ResultJson<>(BusinessCode.NO_ACCESS,false);
                return resultJson;
            }
            userInfo.setUserId(userParam.getUserId());
            userInfo.setStatus(userParam.getStatus());
            if (userInfo.getUserId()== null ){
                return new ResultJson<>(BusinessCode.ILLEGAL_ARG_ERROR,false);
            }

            if (userService.updateUserStatus(userInfo)>0){
                resultJson =  new ResultJson<>(BusinessCode.SUCCESS,true);
            }
            logger.info("resultJson:{}", FastjsonUtil.objectToJson(resultJson));

        }catch (Exception e){
            e.printStackTrace();
        }
        return resultJson;
    }


    @RequestMapping("/updateUserType")
    @ResponseBody
    public ResultJson<Boolean> updateUserType(String body){
        ResultJson<Boolean> resultJson = new ResultJson<>(BusinessCode.UPDATE_ERROR, false);
        try{
            logger.info("UserController--updateUserType body:{}", body);
            UserParam userParam = BusinessBodyConvertUtil.buildBusinessParam(body,UserParam.class);
            UserInfo userInfo = this.getMyInfo();
            if (!"2".equals(userInfo.getUserType())){
                resultJson = new ResultJson<>(BusinessCode.NO_ACCESS,false);
                return resultJson;
            }
            userInfo.setUserId(userParam.getUserId());
            userInfo.setUserType(userParam.getUserType());
            if (userInfo.getUserId()== null || StringUtil.hasBlank(userInfo.getUserType())){
                return new ResultJson<>(BusinessCode.ILLEGAL_ARG_ERROR,false);
            }

            if (userService.updateUserType(userInfo)>0){
                resultJson =  new ResultJson<>(BusinessCode.SUCCESS,true);
            }
            logger.info("resultJson:{}", FastjsonUtil.objectToJson(resultJson));

        }catch (Exception e){
            e.printStackTrace();
        }
        return resultJson;
    }


    @RequestMapping("/findMyPassword")
    @ResponseBody
    public ResultJson<Boolean> findMyPassword(String body){
        UserParam userParam = BusinessBodyConvertUtil.buildBusinessParam(body,UserParam.class);
        UserInfo userInfo = new UserInfo();
        userInfo.setPhone(userParam.getPhone());
        userInfo.setMsg(userParam.getMessage());
        userInfo.setPassword(userParam.getNewPassword());

        if (StringUtil.hasBlank(userInfo.getPhone(),userParam.getNewPassword(),userInfo.getMsg())){
            return new ResultJson<>(BusinessCode.ILLEGAL_ARG_ERROR,false);
        }
        UserInfo oldUserInfo = userService.getFindPassUserInfoByPhone(userInfo);
        ResultJson<Boolean> resultJson = new ResultJson<>(BusinessCode.UPDATE_ERROR, false);
        if (oldUserInfo == null){
            return new ResultJson<>(BusinessCode.UPDATE_ERROR,false);
        }else{
            if (userInfo.getMsg().equals(oldUserInfo.getMsg())){
                if (userService.updateUserPassword(userInfo)>0){
                    resultJson =  new ResultJson<>(BusinessCode.SUCCESS,true);
                }
                logger.info("resultJson:{}", FastjsonUtil.objectToJson(resultJson));
            }
        }

        return resultJson;
    }

}
