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
    private String dataTitle;
    private String dataDesc;
    private String dataType;
    //0--正常  1--删除
    private String status;
    private Date createTime;
    private Date updateTime;
}
