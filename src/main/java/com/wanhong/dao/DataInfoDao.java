package com.wanhong.dao;

import com.wanhong.domain.DataInfo;
import com.wanhong.domain.common.DataQuery;

import java.util.List;

/**
 * @author wangmeng247
 * @date 2018-03-03 18:26
 */
public interface DataInfoDao {
    DataInfo getDataInfoById(DataInfo dataInfo);
    Integer delDataInfoById(DataInfo dataInfo);
    List<DataInfo> findMyData(DataQuery dataQuery);
    List<DataInfo> findDataByPage(DataQuery dataQuery);
    List<DataInfo> findDataByDataTypeAndPage(DataQuery dataQuery);
    Integer saveDataInfo(DataInfo dataInfo);
    Integer getDataInfoCount();
    Integer getDataInfoTypeCount(DataQuery dataQuery);
}
