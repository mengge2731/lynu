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
    Integer saveApplyInfo(ApplyInfo applyInfo);
    /**
     * 分页获取我的申请信息。
     * @param applyQuery
     * @return
     */
    List<ApplyInfo> getMyApplyInfoByPage(ApplyQuery applyQuery);
    List<ApplyInfo> getThireApplyInfoByPage(ApplyQuery applyQuery);
    Integer getMyApplyInfoCount(ApplyQuery applyQuery);
    Integer getThireApplyInfoCount(ApplyQuery applyQuery);

    /**
     * 删除申请信息
     * @param applyInfo
     * @return
     */
    Integer delMyApplyInfoById(ApplyInfo applyInfo);

    /**
     * 同意申请
     * @param applyInfo
     * @return
     */
    Integer agreeApply(ApplyInfo applyInfo);

    /**
     * 拒绝申请
     * @param applyInfo
     * @return
     */
    Integer refuseApply(ApplyInfo applyInfo);

    /**
     * 拒绝申请
     * @param applyInfo
     * @return
     */
    ApplyInfo getApplyInfoByUserIdAndDataId(ApplyInfo applyInfo);

    /**
     *  根据dataid  获取 申请信息。
     * @param dataInfo
     * @return
     */
    ApplyInfo getApplyInfoByDataId(DataInfo dataInfo);
}
