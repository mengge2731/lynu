package com.wanhong.service.impl;

import com.wanhong.common.errorcode.BusinessCode;
import com.wanhong.dao.MessageInfoDao;
import com.wanhong.domain.MessageInfo;
import com.wanhong.domain.ResultJson;
import com.wanhong.domain.vo.MessageResultVo;
import com.wanhong.service.MessageService;
import com.wanhong.util.SendMessageUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.Map;

/**
 * @author wangmeng247
 * @date 2018-03-04 15:32
 */
public class MessageServiceImpl implements MessageService {

    @Autowired
    MessageInfoDao messageInfoDao;
    @Override
    public ResultJson sendMessage(String phone) {
        SendMessageUtil sendMessageUtil = new SendMessageUtil();
        ResultJson result = sendMessageUtil.sendMessage(phone);
        if (BusinessCode.SUCCESS.getCode().equals(result.getCode())){
            Map<String,Object> mapResult = (Map<String,Object>)result.getData();
            String message = (String)mapResult.get("message");
            MessageResultVo messageResultVo = (MessageResultVo)mapResult.get("messageResultVo");
            MessageInfo messageInfoQuery = new MessageInfo();
            messageInfoQuery.setPhone(phone);
            messageInfoQuery.setMessage(message);
            Date expireTime = new Date();
            expireTime.setMinutes(expireTime.getMinutes() + 20);
            messageInfoQuery.setExpireTime(expireTime);
            MessageInfo messageInfo = this.getMessageInfoByPhone(messageInfoQuery);
            if (messageInfo != null){
                this.updateMessageInfoByPhone(messageInfoQuery);
            }else {
                this.saveMessageInfo(messageInfoQuery);
            }
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
