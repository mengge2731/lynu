package com.wanhong.domain.vo;

/**
 * @author wangmeng247
 * @date 2018-03-18 11:38
 */
public class FileInfoVo {
    private Long fileId;
    //文件真实名称
    private String fileRealName;

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public String getFileRealName() {
        return fileRealName;
    }

    public void setFileRealName(String fileRealName) {
        this.fileRealName = fileRealName;
    }

}
