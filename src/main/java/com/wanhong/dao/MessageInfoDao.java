package com.wanhong.dao;

import com.wanhong.domain.MessageInfo;

/**
 * @author wangmeng247
 * @date 2018-03-04 16:08
 */
public interface MessageInfoDao {
    Integer saveMessageInfo(MessageInfo messageInfo);
    MessageInfo getMessageInfoByPhone(MessageInfo messageInfo);
    Integer updateMessageInfoByPhone(MessageInfo messageInfo);
}
