package com.wanhong.dao;

import com.wanhong.domain.FileInfo;
import com.wanhong.domain.common.DataQuery;

import java.util.List;

/**
 * @author wangmeng247
 * @date 2018-03-03 18:26
 */
public interface FileInfoDao {
    FileInfo getFileInfoById(FileInfo FileInfo);
    Integer saveFileInfo(FileInfo FileInfo);
}
