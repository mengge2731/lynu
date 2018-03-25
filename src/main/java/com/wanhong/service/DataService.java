package com.wanhong.service;

import com.wanhong.domain.DataInfo;
import com.wanhong.domain.common.Page;
import com.wanhong.domain.param.DataParam;

import java.util.List;

/**
 * @author wangmeng247
 * @date 2018-02-24 11:37
 */
public interface DataService {
    Page<List<DataInfo>> getDataInfoByPage(DataParam dataParam);
    Page<List<DataInfo>> getDataByDataTypeAndPage(DataParam dataParam);
    Integer delDataInfoByDataId(DataInfo dataInfo);
    Integer updateDataInfo(DataInfo dataInfo);
    DataInfo findDataInfoByDataId(DataInfo dataInfo);
    Integer saveDataInfo(DataInfo dataInfo);
    //我发布的数据共享
    Page<List<DataInfo>> getMyPubDataByPage(DataParam dataParam);
}
