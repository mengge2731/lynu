package com.wanhong.dao;

import com.wanhong.domain.DataInfo;
import com.wanhong.domain.common.DataQuery;

import java.util.List;

/**
 * @author wangmeng247
 * @date 2018-03-03 18:26
 */
public interface ApplyInfoDao {
//    ApplyInfo getApplyInfoById(ApplyInfo dataInfo);
    List<DataInfo> findMyData(DataQuery dataQuery);
    List<DataInfo> findDataByPage(DataQuery dataQuery);
    Integer saveDataInfo(DataInfo dataInfo);
}
