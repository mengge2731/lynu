package com.wanhong.service.impl;

import com.wanhong.dao.ApplyInfoDao;
import com.wanhong.dao.DataInfoDao;
import com.wanhong.dao.UserInfoDao;
import com.wanhong.domain.ApplyInfo;
import com.wanhong.domain.DataInfo;
import com.wanhong.domain.UserInfo;
import com.wanhong.domain.common.DataQuery;
import com.wanhong.domain.common.Page;
import com.wanhong.domain.common.UserQuery;
import com.wanhong.domain.param.DataParam;
import com.wanhong.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangmeng247
 * @date 2018-03-03 20:04
 */
public class DataServiceImpl implements DataService {
    @Autowired
    DataInfoDao dataInfoDao;
    @Autowired
    ApplyInfoDao applyInfoDao;

    @Override
    public Page<List<DataInfo>> getDataInfoByPage(DataParam dataParam) {
        DataQuery dataQuery = new DataQuery();
        dataQuery.setIndex(dataParam.getIndex());
        dataQuery.setPageSize(dataParam.getPageSize());
        List<DataInfo> dataInfoList = dataInfoDao.findDataByPage(dataQuery);
        handleDataInfo(dataInfoList);
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
        handleDataInfo(dataInfoList);
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

    @Override
    public Page<List<DataInfo>> getMyPubDataByPage(DataParam dataParam) {
        DataQuery dataQuery = new DataQuery();
        dataQuery.setIndex(dataParam.getIndex());
        dataQuery.setPageSize(dataParam.getPageSize());
        dataQuery.setUserId(dataParam.getUserId());
        List<DataInfo> dataInfoListPage = dataInfoDao.findMyData(dataQuery);
        handleDataInfo(dataInfoListPage);
//        List<DataInfo> dataInfoList = new ArrayList<>();
//        //是否有人申请这条数据
//        if (dataInfoListPage != null){
//            for (DataInfo dataInfo : dataInfoListPage){
//                List<ApplyInfo> applyInfoList = applyInfoDao.getApplyInfoByDataId(dataInfo);
//                if (applyInfoList != null && applyInfoList.size()>0){
//                    dataInfo.setHaveApply(true);
//                }else{
//                    dataInfo.setHaveApply(false);
//                }
//                dataInfoList.add(dataInfo);
//            }
//        }
        Integer totalCount = dataInfoDao.findMyDataCount(dataQuery);
        Page page = new Page<>(dataInfoListPage);
        page.setTotalItem(totalCount);
        page.setIndex(dataParam.getIndex());
        page.setPageSize(dataParam.getPageSize());
        return page;
    }

    private void handleDataInfo(List<DataInfo> dataInfoList){
        if (dataInfoList != null && dataInfoList.size()>0){
            for (DataInfo dataInfo:dataInfoList){
                if ("1".equals(dataInfo.getDataType())){
                    dataInfo.setImgUrl("//120.27.37.129:8080/lynu/img/lvyou.png");
                }else if ("2".equals(dataInfo.getDataType())){
                    dataInfo.setImgUrl("//120.27.37.129:8080/lynu/img/wenhua.png");
                }else if ("3".equals(dataInfo.getDataType())){
                    dataInfo.setImgUrl("//120.27.37.129:8080/lynu/img/yidali.png");
                }else{
                    dataInfo.setImgUrl("//120.27.37.129:8080/lynu/img/qita.png");
                }

            }
        }
    }
}
