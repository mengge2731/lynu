package com.wanhong.service;

import com.wanhong.domain.ApplyInfo;
import com.wanhong.domain.DataInfo;
import com.wanhong.domain.common.Page;
import com.wanhong.domain.param.ApplyParam;
import com.wanhong.domain.param.DataParam;
import com.wanhong.domain.vo.DataInfoVo;

import java.util.List;

/**
 * @author wangmeng247
 * @date 2018-03-03 17:47
 */
public interface ApplyService {
    /**
     * 获取我自己申请的记录信息
     * @param applyParam
     * @return
     */
    Page<List<DataInfoVo>> getMyApplyInfoByPage(ApplyParam applyParam);

    /**
     * 获取我发布数据名下的申请信息
     * @param applyParam
     * @return
     */
    Page<List<ApplyInfo>> getThireApplyInfoByPage(ApplyParam applyParam);

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
     * 保存申请信息
     * @param applyInfo
     * @return
     */
    Integer saveApplyInfo(ApplyInfo applyInfo);

    /**
     * 保存申请信息
     * @param applyInfo
     * @return
     */
    Boolean isMyApplyInfo(ApplyInfo applyInfo);

    /**
     * @param applyInfo
     * @return
     */
    ApplyInfo getApplyInfoById(ApplyInfo applyInfo);

    Integer delApplyInfoById(ApplyInfo applyInfo);
}
