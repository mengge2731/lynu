package com.wanhong.controller;

import com.wanhong.common.errorcode.BusinessCode;
import com.wanhong.domain.ApplyInfo;
import com.wanhong.domain.DataInfo;
import com.wanhong.domain.ResultJson;
import com.wanhong.domain.UserInfo;
import com.wanhong.domain.common.Page;
import com.wanhong.domain.param.ApplyParam;
import com.wanhong.domain.param.DataParam;
import com.wanhong.domain.vo.UserInfoVo;
import com.wanhong.service.ApplyService;
import com.wanhong.service.DataService;
import com.wanhong.util.BeanUtil;
import com.wanhong.util.BusinessBodyConvertUtil;
import com.wanhong.util.FastjsonUtil;
import com.wanhong.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.xml.ws.Action;
import java.util.List;

/**
 * @author wangmeng247
 * @date 2018-03-03 17:43
 */
@Controller
@RequestMapping("/function/apply")
public class ApplyController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(ApplyController.class);
    @Autowired
    ApplyService applyService;
    @Autowired
    DataService dataService;

    @RequestMapping("/getMyApplyInfoByPage")
    @ResponseBody
    public ResultJson<Page<List<ApplyInfo>>> getMyApplyInfoByPage(String body){
        ResultJson<Page<List<ApplyInfo>>> resultJson = new ResultJson<>(BusinessCode.UNKNOWN_ERROR);
        try{
            ApplyParam applyParam = BusinessBodyConvertUtil.buildBusinessParam(body,ApplyParam.class);
            UserInfo userInfo = this.getMyInfo();
            applyParam.setApplyUserId(userInfo.getUserId());
            Page<List<ApplyInfo>> applyInfoPage =applyService.getMyApplyInfoByPage(applyParam);
            resultJson = new ResultJson<>(BusinessCode.SUCCESS,applyInfoPage);
            logger.info("resultJson:{}", FastjsonUtil.objectToJson(resultJson));
        }catch (Exception e){
            e.printStackTrace();
        }

        return resultJson;
    }

    @RequestMapping("/getThireApplyInfoByPage")
    @ResponseBody
    public ResultJson<Page<List<ApplyInfo>>> getThireApplyInfoByPage(String body){
        ResultJson<Page<List<ApplyInfo>>> resultJson = new ResultJson<>(BusinessCode.UNKNOWN_ERROR);
        try{
            logger.info("ApplyController--getThireApplyInfoByPage--body:{}",body);
            //dataId
            ApplyParam applyParam = BusinessBodyConvertUtil.buildBusinessParam(body,ApplyParam.class);
            if (applyParam.getDataId() == null){
                resultJson = new ResultJson<>(BusinessCode.ILLEGAL_ARG_ERROR);
            }
            UserInfo userInfo = this.getMyInfo();
            applyParam.setPubDataUserId(userInfo.getUserId());
            Page<List<ApplyInfo>> applyInfoPage =applyService.getThireApplyInfoByPage(applyParam);
            resultJson = new ResultJson<>(BusinessCode.SUCCESS,applyInfoPage);
            logger.info("resultJson:{}", FastjsonUtil.objectToJson(resultJson));
        }catch (Exception e){
            e.printStackTrace();
        }

        return resultJson;
    }

    @RequestMapping("/delMyApplyInfoById")
    @ResponseBody
    public ResultJson<Boolean> delMyApplyInfoById(String body){
        ApplyParam applyParam = BusinessBodyConvertUtil.buildBusinessParam(body,ApplyParam.class);
        ApplyInfo applyInfo = new ApplyInfo();
        BeanUtil.copyProperties(applyParam,applyInfo);
        Boolean res = false;
        if (applyService.delMyApplyInfoById(applyInfo)>0){
            res = true;
        }
        ResultJson<Boolean> resultJson = new ResultJson<>(BusinessCode.SUCCESS,res);
        logger.info("resultJson:{}", FastjsonUtil.objectToJson(resultJson));
        return resultJson;
    }

    @RequestMapping("/agreeApply")
    @ResponseBody
    public ResultJson<Boolean> agreeApply(String body){
        ApplyParam applyParam = BusinessBodyConvertUtil.buildBusinessParam(body,ApplyParam.class);
        ApplyInfo applyInfo = new ApplyInfo();
        BeanUtil.copyProperties(applyParam,applyInfo);
        Boolean res = false;
        UserInfo userInfo = getMyInfo();
        applyInfo.setPubDataUserId(userInfo.getUserId());
        if (applyService.agreeApply(applyInfo)>0){
            res = true;
        }
        ResultJson<Boolean> resultJson = new ResultJson<>(BusinessCode.SUCCESS,res);
        logger.info("agreeApply--resultJson:{}", FastjsonUtil.objectToJson(resultJson));
        return resultJson;
    }

    @RequestMapping("/refuseApply")
    @ResponseBody
    public ResultJson<Boolean> refuseApply(String body){
        ApplyParam applyParam = BusinessBodyConvertUtil.buildBusinessParam(body,ApplyParam.class);
        ApplyInfo applyInfo = new ApplyInfo();
        BeanUtil.copyProperties(applyParam,applyInfo);
        Boolean res = false;
        UserInfo userInfo = getMyInfo();
        applyInfo.setPubDataUserId(userInfo.getUserId());
        if (applyService.refuseApply(applyInfo)>0){
            res = true;
        }
        ResultJson<Boolean> resultJson = new ResultJson<>(BusinessCode.SUCCESS,res);
        logger.info("refuseApply--resultJson:{}", FastjsonUtil.objectToJson(resultJson));
        return resultJson;
    }

    @RequestMapping("/saveApplyInfo")
    @ResponseBody
    public ResultJson<Boolean> saveApplyInfo(String body){
        ApplyParam applyParam = BusinessBodyConvertUtil.buildBusinessParam(body,ApplyParam.class);
        ApplyInfo applyInfo = new ApplyInfo();
        BeanUtil.copyProperties(applyParam,applyInfo);
        if (StringUtil.hasBlank(applyInfo.getApplyDesc()) || applyInfo.getDataId()==null){
             return new ResultJson<>(BusinessCode.ILLEGAL_ARG_ERROR);
        }
        if (applyInfo.getApplyDesc().length()<20){
            return new ResultJson<>(BusinessCode.ILLEGAL_ARG_ERROR);
        }
        Boolean res = false;
        UserInfo userInfo = getMyInfo();

        applyInfo.setApplyUserId(userInfo.getUserId());
        applyInfo.setApplyUserPhone(userInfo.getPhone());
        applyInfo.setApplyUserName(userInfo.getUserName());
        DataInfo dataInfoQuery = new DataInfo();
        dataInfoQuery.setDataId(applyInfo.getDataId());
        DataInfo dataInfo = dataService.findDataInfoByDataId(dataInfoQuery);
        if (dataInfo.getUserId().equals(userInfo.getUserId())){
            return new ResultJson<>(BusinessCode.APPLY_OWN_DATA);
        }
        applyInfo.setPubDataUserId(dataInfo.getUserId());
        if (applyService.isMyApplyInfo(applyInfo)){
            return new ResultJson<>(BusinessCode.APPLY_AGINE_ERROR);
        }

        if (applyService.saveApplyInfo(applyInfo)>0){
            res = true;
        }

        ResultJson<Boolean> resultJson = new ResultJson<>(BusinessCode.SUCCESS,res);
        logger.info("saveApplyInfo - resultJson:{}", FastjsonUtil.objectToJson(resultJson));
        return resultJson;
    }
}
