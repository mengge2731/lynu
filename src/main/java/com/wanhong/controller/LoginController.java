package com.wanhong.controller;

import com.wanhong.common.errorcode.BusinessCode;
import com.wanhong.common.spring.SpringContextUtil;
import com.wanhong.domain.DataInfo;
import com.wanhong.domain.MessageInfo;
import com.wanhong.domain.ResultJson;
import com.wanhong.domain.UserInfo;
import com.wanhong.domain.common.Page;
import com.wanhong.domain.param.DataParam;
import com.wanhong.domain.param.UserParam;
import com.wanhong.domain.vo.MessageResultVo;
import com.wanhong.domain.vo.UserInfoVo;
import com.wanhong.service.DataService;
import com.wanhong.service.MessageService;
import com.wanhong.service.UserService;
import com.wanhong.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @author wangmeng
 * @date 2018-02-10 11:01
 */
@Controller
@RequestMapping("/function/login")
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    SpringContextUtil springContextUtil;
    @Autowired
    MessageService messageService;
    @Autowired
    UserService userService;
    @Autowired
    DataService dataService;

    @RequestMapping("/submit")
    @ResponseBody
    public ResultJson<UserInfoVo> submit(String body){
        UserParam userParam = BusinessBodyConvertUtil.buildBusinessParam(body, UserParam.class);
        if (StringUtil.hasBlank(userParam.getPassword(),userParam.getPhone()) || !ValidateUtil.validateParaPhone(userParam.getPhone())){
            return new ResultJson<>(BusinessCode.ILLEGAL_ARG_ERROR);
        }
        UserInfo userInfoQuery = new UserInfo();
        BeanUtil.copyProperties(userParam,userInfoQuery);
        UserInfo  userInfo = userService.getUserInfoByPhone(userInfoQuery);
        UserInfoVo userInfoVo = new UserInfoVo();
        BeanUtil.copyProperties(userInfo,userInfoVo);
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes)ra).getRequest();
        request.getSession().setAttribute("userInfo",userInfoVo);
        return new ResultJson<>(BusinessCode.SUCCESS,userInfoVo);
    }

    @RequestMapping("/logout")
    @ResponseBody
    public ResultJson<Boolean> logout(String body){
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes)ra).getRequest();
        request.getSession().removeAttribute("userInfo");
        return new ResultJson<>(BusinessCode.SUCCESS,true);
    }

    @RequestMapping("/getVerifyCode")
    public void getVerifyCode(HttpServletRequest request,HttpServletResponse response){
        response.setHeader("Pragma","No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");

        //生成随机字符串
        String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
        //存入Session, 此处可以根据自己的需求
        HttpSession session = request.getSession();
        session.setAttribute("verifyCode",verifyCode);
        //生成图片
        int w = 100, h = 35;
        try {
            //将图片写入到 response 的输出流即可将图片返回到客户端了
            VerifyCodeUtils.outputImage(w, h , response.getOutputStream(), verifyCode);
        } catch (IOException e) {
            logger.error("生成验证码失败, Cause by: {}", e.getMessage(), e);
        }
    }

    @RequestMapping("/isLogin")
    @ResponseBody
    public ResultJson<Boolean> isLogin(HttpServletRequest request){
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
        if (userInfo == null){
            return new ResultJson<>(BusinessCode.NOT_LOG_IN);
        }
        return new ResultJson<>(BusinessCode.IS_LOG_IN,true);
    }

    @RequestMapping("/sendMsg")
    @ResponseBody
    public ResultJson<MessageResultVo> sendMsg(String body){
        UserParam userParam = BusinessBodyConvertUtil.buildBusinessParam(body, UserParam.class);
        if (StringUtil.hasBlank(userParam.getPhone()) || !ValidateUtil.validateParaPhone(userParam.getPhone())){
            return new ResultJson<>(BusinessCode.ILLEGAL_ARG_ERROR);
        }else{
            return messageService.sendMessage(userParam.getPhone());
        }
    }

    @RequestMapping("/register")
    @ResponseBody
    public ResultJson<Boolean> register(String body){
        ResultJson resultJson = new ResultJson(BusinessCode.REGISTER_ERROR);
        UserParam userParam = BusinessBodyConvertUtil.buildBusinessParam(body, UserParam.class);
        if (StringUtil.hasBlank(userParam.getPhone(),userParam.getPassword(),userParam.getMessage())
                || !ValidateUtil.validateParaPhone(userParam.getPhone())){
            return new ResultJson<>(BusinessCode.ILLEGAL_ARG_ERROR);
        }
        try {
            MessageInfo messageInfo = new MessageInfo();
            messageInfo.setPhone(userParam.getPhone());
            MessageInfo messageInfoResult = messageService.getMessageInfoByPhone(messageInfo);
            if (messageInfoResult != null) {
                if (userParam.getMessage().equals(messageInfoResult.getMessage())) {
                    if (messageInfoResult.getExpireTime().compareTo(new Date()) > 0) {
                        UserInfo userInfoQuery = new UserInfo();
                        BeanUtil.copyProperties(userParam, userInfoQuery);
                        UserInfo userInfo = userService.getUserInfoByPhone(userInfoQuery);
                        if (userInfo == null){//从未注册过
                            UserInfo userInfoResult = userService.saveUserInfo(userInfoQuery);
                            if (userInfoResult != null){
                                resultJson = new ResultJson<>(BusinessCode.SUCCESS, true);
                            }else{
                                resultJson = new ResultJson<>(BusinessCode.REGISTER_ERROR);
                            }
                        }else{
                            resultJson = new ResultJson<>(BusinessCode.REGISTER_AGINE);
                        }

                    } else {
                        resultJson = new ResultJson<>(BusinessCode.MESSAGE_OVER_TIME);
                    }
                } else {
                    resultJson = new ResultJson<>(BusinessCode.MESSAGE_WRONG);
                }
            } else {
                resultJson = new ResultJson<>(BusinessCode.ILLEGAL_ARG_ERROR);
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        return resultJson;
    }


    @RequestMapping("/getFirstPageData")
    @ResponseBody
    public ResultJson<Page<List<DataInfo>>> getFirstPageData(){

        DataParam dataParam = new DataParam();
        dataParam.setIndex(1);
        dataParam.setPageSize(10);
        ResultJson<Page<List<DataInfo>>> resultJson = new ResultJson<>(BusinessCode.UNKNOWN_ERROR);
        try{
            Page<List<DataInfo>> dataInfoPage =dataService.getDataInfoByPage(dataParam);
            dataInfoPage.setIndex(dataParam.getIndex());
            dataInfoPage.setPageSize(dataParam.getPageSize());
            resultJson = new ResultJson<>(BusinessCode.SUCCESS,dataInfoPage);
        }catch (Exception e){
            logger.error("getFirstPageData error:{}",e);
        }

        logger.info("resultJson:{}", FastjsonUtil.objectToJson(resultJson));
        return resultJson;
    }
}
