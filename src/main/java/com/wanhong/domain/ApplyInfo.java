package com.wanhong.domain;

import java.util.Date;

/**
 * @author wangmeng247
 * @date 2018-02-24 11:38
 */
public class ApplyInfo {
    //申请记录id
    private Long applyId;
    private Long dataId;
    //申请者id
    private Long applyUserId;
    //发布数据人的id
    private Long pubDataUserId;
    //申请者姓名
    private String applyUserName;
    //申请者手机号
    private String applyUserPhone;
    //申请者附言
    private String applyDesc;
    //申请时间
    private Date applyTime;
    //回复时间
    private Date replyTime;
    //0--正常  1--回复   2--拒绝
    private String status;
    private Date createTime;
    private Date updateTime;

    public Long getApplyId() {
        return applyId;
    }

    public void setApplyId(Long applyId) {
        this.applyId = applyId;
    }

    public Long getDataId() {
        return dataId;
    }

    public void setDataId(Long dataId) {
        this.dataId = dataId;
    }

    public Long getApplyUserId() {
        return applyUserId;
    }

    public void setApplyUserId(Long applyUserId) {
        this.applyUserId = applyUserId;
    }

    public Long getPubDataUserId() {
        return pubDataUserId;
    }

    public void setPubDataUserId(Long pubDataUserId) {
        this.pubDataUserId = pubDataUserId;
    }

    public String getApplyUserName() {
        return applyUserName;
    }

    public void setApplyUserName(String applyUserName) {
        this.applyUserName = applyUserName;
    }

    public String getApplyUserPhone() {
        return applyUserPhone;
    }

    public void setApplyUserPhone(String applyUserPhone) {
        this.applyUserPhone = applyUserPhone;
    }

    public String getApplyDesc() {
        return applyDesc;
    }

    public void setApplyDesc(String applyDesc) {
        this.applyDesc = applyDesc;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public Date getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(Date replyTime) {
        this.replyTime = replyTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
