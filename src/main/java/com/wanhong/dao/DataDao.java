package com.wanhong.dao;

import com.wanhong.domain.DataInfo;

/**
 * @author wangmeng247
 * @date 2018-03-03 18:26
 */
public interface DataDao {
    DataInfo getDataInfoById(DataInfo dataInfo);
}
