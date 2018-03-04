package com.wanhong.dao;

import com.wanhong.domain.ApplyInfo;
import com.wanhong.domain.DataInfo;
import com.wanhong.domain.common.ApplyQuery;

import java.util.List;

/**
 * @author wangmeng247
 * @date 2018-03-03 18:26
 */
public interface ApplyInfoDao {
//    ApplyInfo getApplyInfoById(ApplyInfo dataInfo);
    List<DataInfo> findMyData(ApplyQuery applyQuery);
    List<DataInfo> findDataByPage(ApplyQuery applyQuery);
    Integer saveApplyInfo(ApplyInfo applyInfo);
}
