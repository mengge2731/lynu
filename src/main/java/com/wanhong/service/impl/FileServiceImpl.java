package com.wanhong.service.impl;

import com.wanhong.dao.FileInfoDao;
import com.wanhong.domain.FileInfo;
import com.wanhong.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author wangmeng247
 * @date 2018-03-18 18:34
 */
public class FileServiceImpl implements FileService {
    @Autowired
    FileInfoDao fileInfoDao;

    @Override
    public Integer saveFileInfo(FileInfo fileInfo) {
        return fileInfoDao.saveFileInfo(fileInfo);
    }

    @Override
    public FileInfo getFileInfoById(FileInfo fileInfo) {
        return fileInfoDao.getFileInfoById(fileInfo);
    }
}
