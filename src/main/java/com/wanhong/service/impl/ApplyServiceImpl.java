package com.wanhong.service.impl;

import com.wanhong.dao.ApplyInfoDao;
import com.wanhong.dao.DataInfoDao;
import com.wanhong.domain.ApplyInfo;
import com.wanhong.domain.DataInfo;
import com.wanhong.domain.common.ApplyQuery;
import com.wanhong.domain.common.Page;
import com.wanhong.domain.param.ApplyParam;
import com.wanhong.service.ApplyService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author wangmeng247
 * @date 2018-03-03 21:06
 */
public class ApplyServiceImpl implements ApplyService {

    @Autowired
    ApplyInfoDao applyInfoDao;
    @Autowired
    DataInfoDao dataInfoDao;
    @Override
    public Page<List<ApplyInfo>> getMyApplyInfoByPage(ApplyParam applyParam) {
        ApplyQuery applyQuery = new ApplyQuery();
        applyQuery.setIndex(applyParam.getIndex());
        applyQuery.setPageSize(applyParam.getPageSize());
        List<ApplyInfo> applyInfoList = applyInfoDao.getMyApplyInfoByPage(applyQuery);
        Integer totalCount = applyInfoDao.getMyApplyInfoCount(applyQuery);
        Page page = new Page<>(applyInfoList);
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
}
