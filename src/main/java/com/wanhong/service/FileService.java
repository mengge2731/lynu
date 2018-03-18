package com.wanhong.service;

import com.wanhong.domain.FileInfo;

/**
 * @author wangmeng247
 * @date 2018-03-18 18:32
 */
public interface FileService {
    Integer saveFileInfo(FileInfo fileInfo);
    FileInfo getFileInfoById(FileInfo fileInfo);
}
