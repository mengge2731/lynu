package com.wanhong.controller;

import com.wanhong.common.errorcode.BusinessCode;
import com.wanhong.domain.DataInfo;
import com.wanhong.domain.ResultJson;
import com.wanhong.domain.UserInfo;
import com.wanhong.domain.common.Page;
import com.wanhong.domain.param.DataParam;
import com.wanhong.domain.param.UserParam;
import com.wanhong.domain.vo.UserInfoVo;
import com.wanhong.service.DataService;
import com.wanhong.service.UserService;
import com.wanhong.util.BeanUtil;
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
public class DataController extends BaseController{
    private static final Logger logger = LoggerFactory.getLogger(DataController.class);
    @Autowired
    private DataService dataService;

    @RequestMapping("/getDataByPage")
    @ResponseBody
    public ResultJson<Page<List<DataInfo>>> getDataByPage(String body){
        ResultJson<Page<List<DataInfo>>> resultJson = null;
        try{
            DataParam dataParam = BusinessBodyConvertUtil.buildBusinessParam(body,DataParam.class);
            Page<List<DataInfo>> dataInfoPage =dataService.getDataInfoByPage(dataParam);
            dataInfoPage.setIndex(dataParam.getIndex());
            dataInfoPage.setPageSize(dataParam.getPageSize());
            resultJson = new ResultJson<>(BusinessCode.SUCCESS,dataInfoPage);
            logger.info("DataController--getDataByPage--resultJson:{}", FastjsonUtil.objectToJson(resultJson));
        }catch (Exception e){
            e.printStackTrace();
        }

        return resultJson;
    }

    @RequestMapping("/getDataByDataTypeAndPage")
    @ResponseBody
    public ResultJson<Page<List<DataInfo>>> getDataByDataTypeAndPage(String body){
        ResultJson<Page<List<DataInfo>>> resultJson =null;
        try{
            DataParam dataParam = BusinessBodyConvertUtil.buildBusinessParam(body,DataParam.class);
            if ("0".equals(dataParam.getDataType())){
                dataParam.setDataType(null);
            }
            Page<List<DataInfo>> dataInfoPage =dataService.getDataByDataTypeAndPage(dataParam);
            dataInfoPage.setIndex(dataParam.getIndex());
            dataInfoPage.setPageSize(dataParam.getPageSize());
            resultJson = new ResultJson<>(BusinessCode.SUCCESS,dataInfoPage);
            logger.info("DataController--getDataByDataTypeAndPage--resultJson:{}", FastjsonUtil.objectToJson(resultJson));
        }catch (Exception e){
            e.printStackTrace();
        }

        return resultJson;
    }

    @RequestMapping("/getMyPubDataByPage")
    @ResponseBody
    public ResultJson<Page<List<DataInfo>>> getMyPubDataByPage(String body){
        DataParam dataParam = BusinessBodyConvertUtil.buildBusinessParam(body,DataParam.class);
        UserInfo userInfo = this.getMyInfo();
        dataParam.setUserId(userInfo.getUserId());
        Page<List<DataInfo>> dataInfoPage =dataService.getMyPubDataByPage(dataParam);
        dataInfoPage.setIndex(dataParam.getIndex());
        dataInfoPage.setPageSize(dataParam.getPageSize());
        ResultJson<Page<List<DataInfo>>> resultJson = new ResultJson<>(BusinessCode.SUCCESS,dataInfoPage);
        logger.info("DataController--getMyPubDataByPage--resultJson:{}", FastjsonUtil.objectToJson(resultJson));
        return resultJson;
    }

    @RequestMapping("/getDataInfo")
    @ResponseBody
    public ResultJson<DataInfo> getDataInfo(String body){
        DataParam dataParam = BusinessBodyConvertUtil.buildBusinessParam(body,DataParam.class);
        DataInfo dataInfo = new DataInfo();
        BeanUtil.copyProperties(dataParam,dataInfo);
        dataInfo.setDataId(dataParam.getDataId());
        DataInfo dataInfoResult =dataService.findDataInfoByDataId(dataInfo);
        ResultJson<DataInfo> resultJson = new ResultJson<>(BusinessCode.SUCCESS,dataInfoResult);
        logger.info("DataController--getDataInfo--resultJson:{}", FastjsonUtil.objectToJson(resultJson));
        return resultJson;
    }


    @RequestMapping("/saveDataInfo")
    @ResponseBody
    public ResultJson<Boolean> saveDataInfo(String body){
        ResultJson<Boolean> resultJson = null;
        try{
            DataParam dataParam = BusinessBodyConvertUtil.buildBusinessParam(body,DataParam.class);
            DataInfo dataInfo = new DataInfo();
            BeanUtil.copyProperties(dataParam,dataInfo);
            UserInfo userInfo = this.getMyInfo();
            dataInfo.setPubDesc(userInfo.getDesc());
            dataInfo.setPubUser(userInfo.getUserName());
            dataInfo.setUserId(userInfo.getUserId());
            Integer res =dataService.saveDataInfo(dataInfo);
            boolean flag = false;
            if (res>0){
                flag = true;
            }
            resultJson = new ResultJson<>(BusinessCode.SUCCESS,flag);
        }catch (Exception e){
            e.printStackTrace();
        }

        logger.info("DataController--saveDataInfo--resultJson:{}", FastjsonUtil.objectToJson(resultJson));
        return resultJson;
    }

    @RequestMapping("/delDataInfo")
    @ResponseBody
    public ResultJson<Boolean> delDataInfo(String body){
        DataParam dataParam = BusinessBodyConvertUtil.buildBusinessParam(body,DataParam.class);
        DataInfo dataInfo = new DataInfo();
        BeanUtil.copyProperties(dataParam,dataInfo);
        Integer res =dataService.delDataInfoByDataId(dataInfo);
        boolean flag = false;
        if (res>0){
            flag = true;
        }
        ResultJson<Boolean> resultJson = new ResultJson<>(BusinessCode.SUCCESS,flag);
        logger.info("DataController--delDataInfo--resultJson:{}", FastjsonUtil.objectToJson(resultJson));
        return resultJson;
    }

}
