package com.wanhong.domain;

import com.wanhong.domain.common.Page;

import java.util.Date;

/**
 * @author wangmeng247
 * @date 2018-02-09 11:39
 */
public class UserInfo{
    private Long userId;
    private String userName;
    private String nickName;
    private String password;
    private String phone;
    /**
     * 1--普通用户;2--管理员;
     */
    private String userType;
    private String msg;
    /**
     * 0--正常状态; 1--被删除;
     */
    private String status;
    private Date msgExpired;
    private Date createTime;
    private Date updateTime;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getMsgExpired() {
        return msgExpired;
    }

    public void setMsgExpired(Date msgExpired) {
        this.msgExpired = msgExpired;
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
