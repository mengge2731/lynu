package com.wanhong.service.impl;

import com.alibaba.fastjson.JSON;
import com.wanhong.controller.ApplyController;
import com.wanhong.dao.ApplyInfoDao;
import com.wanhong.dao.DataInfoDao;
import com.wanhong.domain.ApplyInfo;
import com.wanhong.domain.DataInfo;
import com.wanhong.domain.UserInfo;
import com.wanhong.domain.common.ApplyQuery;
import com.wanhong.domain.common.Page;
import com.wanhong.domain.param.ApplyParam;
import com.wanhong.domain.vo.DataInfoVo;
import com.wanhong.service.ApplyService;
import com.wanhong.util.BeanUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangmeng247
 * @date 2018-03-03 21:06
 */
public class ApplyServiceImpl implements ApplyService {
    private static final Logger logger = LoggerFactory.getLogger(ApplyServiceImpl.class);
    @Autowired
    ApplyInfoDao applyInfoDao;
    @Autowired
    DataInfoDao dataInfoDao;
    @Override
    public Page<List<DataInfoVo>> getMyApplyInfoByPage(ApplyParam applyParam) {
        ApplyQuery applyQuery = new ApplyQuery();
        applyQuery.setIndex(applyParam.getIndex());
        applyQuery.setPageSize(applyParam.getPageSize());
        applyQuery.setApplyUserId(applyParam.getApplyUserId());
        logger.info("ApplyServiceImpl--getMyApplyInfoByPage--applyQuery:{}", JSON.toJSONString(applyQuery));
        List<ApplyInfo> applyInfoList = applyInfoDao.getMyApplyInfoByPage(applyQuery);
        Integer totalCount = applyInfoDao.getMyApplyInfoCount(applyQuery);
        List<DataInfoVo> dataInfoList = new ArrayList<>();
        if (applyInfoList != null && applyInfoList.size()>0){
            for (ApplyInfo applyInfo : applyInfoList){
                DataInfo dataInfoQuery = new DataInfo();
                dataInfoQuery.setDataId(applyInfo.getDataId());
                DataInfo dataInfo = dataInfoDao.getDataInfoById(dataInfoQuery);
                if (dataInfo == null){
                    continue;
                }
                DataInfoVo dataInfoVo = new DataInfoVo();
                logger.info("=============dataInfo:{}",JSON.toJSONString(dataInfo));
                BeanUtil.copyProperties(dataInfo,dataInfoVo);
                dataInfoVo.setApplyId(applyInfo.getApplyId());
                dataInfoList.add(dataInfoVo);
            }
        }
        Page page = new Page<>(dataInfoList);
        page.setTotalItem(totalCount);
        page.setIndex(applyParam.getIndex());
        page.setPageSize(applyParam.getPageSize());
        return page;
    }

    @Override
    public Page<List<ApplyInfo>> getThireApplyInfoByPage(ApplyParam applyParam) {
        ApplyQuery applyQuery = new ApplyQuery();
        applyQuery.setIndex(applyParam.getIndex());
        applyQuery.setPageSize(applyParam.getPageSize());
        applyQuery.setPubDataUserId(applyParam.getPubDataUserId());
        applyQuery.setDataId(applyParam.getDataId());
        List<ApplyInfo> applyInfoList = applyInfoDao.getThireApplyInfoByPage(applyQuery);
        Integer totalCount = applyInfoDao.getThireApplyInfoCount(applyQuery);
        Page page = new Page<>(applyInfoList);
        page.setTotalItem(totalCount);
        page.setIndex(applyParam.getIndex());
        page.setPageSize(applyParam.getPageSize());
        return page;
    }

    @Override
    public Integer delMyApplyInfoById(ApplyInfo applyInfo) {
        return applyInfoDao.delMyApplyInfoById(applyInfo);
    }

    @Override
    public Integer agreeApply(ApplyInfo applyInfo) {
        return applyInfoDao.agreeApply(applyInfo);
    }

    @Override
    public Integer refuseApply(ApplyInfo applyInfo) {
        return applyInfoDao.refuseApply(applyInfo);
    }

    @Override
    public Integer saveApplyInfo(ApplyInfo applyInfo) {
        return applyInfoDao.saveApplyInfo(applyInfo);
    }

    @Override
    public Boolean isMyApplyInfo(ApplyInfo applyInfo) {
        ApplyInfo applyInfoResult = applyInfoDao.getApplyInfoByUserIdAndDataId(applyInfo);
        if (applyInfoResult==null){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public ApplyInfo getApplyInfoById(ApplyInfo applyInfo) {
        return applyInfoDao.getApplyInfoById(applyInfo);
    }
}
