package com.wanhong.controller;

import com.wanhong.common.errorcode.BusinessCode;
import com.wanhong.domain.DataInfo;
import com.wanhong.domain.ResultJson;
import com.wanhong.domain.UserInfo;
import com.wanhong.domain.common.Page;
import com.wanhong.domain.param.DataParam;
import com.wanhong.domain.param.UserParam;
import com.wanhong.service.DataService;
import com.wanhong.service.UserService;
import com.wanhong.util.BusinessBodyConvertUtil;
import com.wanhong.util.FastjsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author wangmeng247
 * @date 2018-02-24 11:37
 */
@Controller
@RequestMapping("/function/data")
public class DataController {
    private static final Logger logger = LoggerFactory.getLogger(DataController.class);
    @Autowired
    private DataService dataService;

    @RequestMapping("/getDataByPage")
    @ResponseBody
    public ResultJson<Page<List<DataInfo>>> getDataByPage(String body){
        DataParam dataParam = BusinessBodyConvertUtil.buildBusinessParam(body,DataParam.class);
        Page<List<DataInfo>> dataInfoPage =dataService.getDataInfoByPage(dataParam);
        dataInfoPage.setIndex(dataParam.getIndex());
        dataInfoPage.setPageSize(dataParam.getPageSize());
        ResultJson<Page<List<DataInfo>>> resultJson = new ResultJson<>(BusinessCode.SUCCESS,dataInfoPage);
        logger.info("resultJson:{}", FastjsonUtil.objectToJson(resultJson));
        return resultJson;
    }

    @RequestMapping("/getDataByDataTypeAndPage")
    @ResponseBody
    public ResultJson<Page<List<DataInfo>>> getDataByDataTypeAndPage(String body){
        DataParam dataParam = BusinessBodyConvertUtil.buildBusinessParam(body,DataParam.class);
        Page<List<DataInfo>> dataInfoPage =dataService.getDataByDataTypeAndPage(dataParam);
        dataInfoPage.setIndex(dataParam.getIndex());
        dataInfoPage.setPageSize(dataParam.getPageSize());
        ResultJson<Page<List<DataInfo>>> resultJson = new ResultJson<>(BusinessCode.SUCCESS,dataInfoPage);
        logger.info("resultJson:{}", FastjsonUtil.objectToJson(resultJson));
        return resultJson;
    }

}
