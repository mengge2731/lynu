package com.wanhong.service.impl;

import com.wanhong.dao.DataInfoDao;
import com.wanhong.dao.UserInfoDao;
import com.wanhong.domain.DataInfo;
import com.wanhong.domain.UserInfo;
import com.wanhong.domain.common.DataQuery;
import com.wanhong.domain.common.Page;
import com.wanhong.domain.common.UserQuery;
import com.wanhong.domain.param.DataParam;
import com.wanhong.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author wangmeng247
 * @date 2018-03-03 20:04
 */
public class DataServiceImpl implements DataService {
    @Autowired
    DataInfoDao dataInfoDao;

    @Override
    public Page<List<DataInfo>> getDataInfoByPage(DataParam dataParam) {
        DataQuery dataQuery = new DataQuery();
        dataQuery.setIndex(dataParam.getIndex());
        dataQuery.setPageSize(dataParam.getPageSize());
        List<DataInfo> dataInfoList = dataInfoDao.findDataByPage(dataQuery);
        Integer totalCount = dataInfoDao.getDataInfoCount();
        Page page = new Page<>(dataInfoList);
        page.setTotalItem(totalCount);
        page.setIndex(dataParam.getIndex());
        page.setPageSize(dataParam.getPageSize());
        return page;
    }

    @Override
    public Page<List<DataInfo>> getDataByDataTypeAndPage(DataParam dataParam) {
        DataQuery dataQuery = new DataQuery();
        dataQuery.setIndex(dataParam.getIndex());
        dataQuery.setPageSize(dataParam.getPageSize());
        dataQuery.setDataType(dataParam.getDataType());
        List<DataInfo> dataInfoList = dataInfoDao.findDataByDataTypeAndPage(dataQuery);
        Integer totalCount = dataInfoDao.getDataInfoTypeCount(dataQuery);
        Page page = new Page<>(dataInfoList);
        page.setTotalItem(totalCount);
        page.setIndex(dataParam.getIndex());
        page.setPageSize(dataParam.getPageSize());
        return page;
    }

    @Override
    public Integer delDataInfoByDataId(DataInfo dataInfo) {
        return dataInfoDao.delDataInfoById(dataInfo);
    }

    @Override
    public Integer updateDataInfo(DataInfo dataInfo) {
        return null;
    }

    @Override
    public DataInfo findDataInfoByDataId(DataInfo dataInfo) {
        return dataInfoDao.getDataInfoById(dataInfo);
    }

    @Override
    public Integer saveDataInfo(DataInfo dataInfo) {
        return dataInfoDao.saveDataInfo(dataInfo);
    }
}
