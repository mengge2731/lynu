package com.wanhong.domain.vo;

import java.util.Date;

/**
 * @author wangmeng247
 * @date 2018-03-03 14:02
 */
public class UserInfoVo {
    private Long userId;
    private String userName;
    private String nickName;
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
}
