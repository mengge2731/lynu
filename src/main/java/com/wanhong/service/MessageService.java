package com.wanhong.service;

import com.wanhong.domain.MessageInfo;
import com.wanhong.domain.ResultJson;
import com.wanhong.domain.vo.MessageResultVo;

/**
 * @author wangmeng247
 * @date 2018-03-04 15:31
 */
public interface MessageService {
    ResultJson<MessageResultVo> sendMessage(String phone);
    ResultJson<MessageResultVo> sendFindPasswordMessage(String phone,String msg);
    Integer saveMessageInfo(MessageInfo messageInfo);
    MessageInfo getMessageInfoByPhone(MessageInfo messageInfo);
    Integer updateMessageInfoByPhone(MessageInfo messageInfo);
}
