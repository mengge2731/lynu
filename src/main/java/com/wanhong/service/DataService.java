package com.wanhong.service;

import com.wanhong.domain.UserInfo;
import com.wanhong.domain.common.Page;

import java.util.List;

/**
 * @author wangmeng247
 * @date 2018-02-24 11:37
 */
public interface DataService {
    List<UserInfo> getUserInfoList();
    Page<List<UserInfo>> getUserInfoByPage(Integer index);
}
