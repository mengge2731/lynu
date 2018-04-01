package com.wanhong.service.impl;

import com.alibaba.fastjson.JSON;
import com.wanhong.common.errorcode.BusinessCode;
import com.wanhong.dao.MessageInfoDao;
import com.wanhong.dao.UserInfoDao;
import com.wanhong.domain.MessageInfo;
import com.wanhong.domain.ResultJson;
import com.wanhong.domain.UserInfo;
import com.wanhong.domain.vo.MessageResultVo;
import com.wanhong.service.MessageService;
import com.wanhong.service.UserService;
import com.wanhong.util.SendMessageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.Map;

/**
 * @author wangmeng247
 * @date 2018-03-04 15:32
 */
public class MessageServiceImpl implements MessageService {
    private static Logger logger = LoggerFactory.getLogger(SendMessageUtil.class);
    @Autowired
    MessageInfoDao messageInfoDao;
    @Autowired
    UserInfoDao userInfoDao;
    @Autowired
    UserService userService;
    @Override
    public ResultJson sendMessage(String phone) {
        UserInfo userInfo = new UserInfo();
        userInfo.setPhone(phone);
        if(userService.getUserInfoByPhone(userInfo)!= null){
            return new ResultJson(BusinessCode.REGISTER_AGINE);
        }
        MessageInfo messageInfoQuery = new MessageInfo();
        messageInfoQuery.setPhone(phone);
        MessageInfo messageInfo = this.getMessageInfoByPhone(messageInfoQuery);
        //如果之前发过一条短信
        if (messageInfo != null ){
            // 如果之前发过一条短信 并且短信已经过期则, (Date类型比较：相等则返回0，ExpireTime大返回1，否则返回-1)
            if (messageInfo.getExpireTime().compareTo(new Date())<=0) {
                return this.sendAndUpdate(phone);
            }else{//如果之前发过，并且短信没有过期，则返回发送成功。
                return new ResultJson(BusinessCode.SEND_MESSAGE_SUCCESS);
            }
        }else {//如果之前没法送过短信。则重新发送，并记录这条数据。
            return this.sendAndSave(phone);
        }
    }

    @Override
    public ResultJson<MessageResultVo> sendFindPasswordMessage(String phone) {
        UserInfo userInfo = new UserInfo();
        userInfo.setPhone(phone);
        try{
            UserInfo oldUserInfo = userService.getFindPassUserInfoByPhone(userInfo);
            logger.info("sendFindPasswordMessage--oldUserInfo--MsgExpired:{}",oldUserInfo.getMsgExpired());
            if(oldUserInfo!= null){
                if (oldUserInfo.getMsgExpired()==null || oldUserInfo.getMsgExpired().compareTo(new Date())<=0){//过期
                    return this.sendAndUpdateUserInfo(phone);
                }else{//没过期
                    return new ResultJson(BusinessCode.SEND_AGINE);
                }
            }else{
                return new ResultJson(BusinessCode.FORBIDDEN);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResultJson(BusinessCode.UNKNOWN_ERROR);
    }

    private ResultJson sendAndUpdate(String phone){
        MessageInfo messageInfoQuery = new MessageInfo();
        messageInfoQuery.setPhone(phone);
        SendMessageUtil sendMessageUtil = new SendMessageUtil();
        ResultJson result = sendMessageUtil.sendMessage(phone);
        if (BusinessCode.SUCCESS.getCode().equals(result.getCode())) {
            Map<String, Object> mapResult = (Map<String, Object>) result.getData();
            String message = (String) mapResult.get("message");
            MessageResultVo messageResultVo = (MessageResultVo) mapResult.get("messageResultVo");
            result.setData(messageResultVo);
            messageInfoQuery.setMessage(message);
            Date expireTime = new Date();
            expireTime.setMinutes(expireTime.getMinutes() + 20);
            messageInfoQuery.setExpireTime(expireTime);
            this.updateMessageInfoByPhone(messageInfoQuery);
            logger.info("MessageServiceImpl--sendAndUpdate--result:{}",result);
        }
        return result;
    }

    private ResultJson sendAndSave(String phone){
        MessageInfo messageInfoQuery = new MessageInfo();
        messageInfoQuery.setPhone(phone);
        SendMessageUtil sendMessageUtil = new SendMessageUtil();
        ResultJson result = sendMessageUtil.sendMessage(phone);
        if (BusinessCode.SUCCESS.getCode().equals(result.getCode())) {
            Map<String, Object> mapResult = (Map<String, Object>) result.getData();
            String message = (String) mapResult.get("message");
            MessageResultVo messageResultVo = (MessageResultVo) mapResult.get("messageResultVo");
            result.setData(messageResultVo);
            messageInfoQuery.setMessage(message);
            Date expireTime = new Date();
            expireTime.setMinutes(expireTime.getMinutes() + 20);
            messageInfoQuery.setExpireTime(expireTime);
            this.saveMessageInfo(messageInfoQuery);
            logger.info("MessageServiceImpl--sendAndSave--result:{}", JSON.toJSONString(result));
        }
        return result;
    }

    private ResultJson sendAndUpdateUserInfo(String phone){
        UserInfo userInfo = new UserInfo();
        userInfo.setPhone(phone);
        SendMessageUtil sendMessageUtil = new SendMessageUtil();
        ResultJson result = sendMessageUtil.sendMessage(phone);
        if (BusinessCode.SUCCESS.getCode().equals(result.getCode())) {
            Map<String, Object> mapResult = (Map<String, Object>) result.getData();
            String message = (String) mapResult.get("message");
            MessageResultVo messageResultVo = (MessageResultVo) mapResult.get("messageResultVo");
            result.setData(messageResultVo);
            userInfo.setMsg(message);
            Date expireTime = new Date();
            expireTime.setMinutes(expireTime.getMinutes() + 20);
            userInfo.setMsgExpired(expireTime);
            userInfoDao.updateUserMsgAndExpired(userInfo);
            logger.info("MessageServiceImpl--sendAndUpdateUserInfo--result:{}",JSON.toJSONString(result));
        }
        return result;
    }
    @Override
    public Integer saveMessageInfo(MessageInfo messageInfo) {
        return messageInfoDao.saveMessageInfo(messageInfo);
    }

    @Override
    public MessageInfo getMessageInfoByPhone(MessageInfo messageInfo) {
        return messageInfoDao.getMessageInfoByPhone(messageInfo);
    }

    @Override
    public Integer updateMessageInfoByPhone(MessageInfo messageInfo) {
        return messageInfoDao.updateMessageInfoByPhone(messageInfo);
    }
}
