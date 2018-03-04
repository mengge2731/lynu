package com.wanhong.domain;

import java.util.Date;

/**
 * @author wangmeng247
 * @date 2018-02-24 11:38
 */
public class DataInfo {
    private Long dataId;
    private Long userId;
    private Long fileId;
    //数据title
    private String dataTitle;
    //数据量
    private String dataNum;
    //数据描述
    private String dataDesc;
    //数据类型  1-旅游相关 2-文化相关  3-意大利相关
    private String dataType;
    //发布人
    private String pubUser;
    //发布人简介
    private String pubDesc;
    private boolean isHaveApply;
    //0--正常  1--删除
    private String status;
    private Date createTime;
    private Date updateTime;

    public Long getDataId() {
        return dataId;
    }

    public void setDataId(Long dataId) {
        this.dataId = dataId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public String getDataTitle() {
        return dataTitle;
    }

    public void setDataTitle(String dataTitle) {
        this.dataTitle = dataTitle;
    }

    public String getDataNum() {
        return dataNum;
    }

    public void setDataNum(String dataNum) {
        this.dataNum = dataNum;
    }

    public String getDataDesc() {
        return dataDesc;
    }

    public void setDataDesc(String dataDesc) {
        this.dataDesc = dataDesc;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getPubUser() {
        return pubUser;
    }

    public void setPubUser(String pubUser) {
        this.pubUser = pubUser;
    }

    public String getPubDesc() {
        return pubDesc;
    }

    public void setPubDesc(String pubDesc) {
        this.pubDesc = pubDesc;
    }

    public boolean isHaveApply() {
        return isHaveApply;
    }

    public void setHaveApply(boolean haveApply) {
        isHaveApply = haveApply;
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
