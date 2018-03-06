package com.wanhong.controller;

import com.wanhong.common.errorcode.BusinessCode;
import com.wanhong.domain.ApplyInfo;
import com.wanhong.domain.DataInfo;
import com.wanhong.domain.ResultJson;
import com.wanhong.domain.common.Page;
import com.wanhong.domain.param.ApplyParam;
import com.wanhong.domain.param.DataParam;
import com.wanhong.service.ApplyService;
import com.wanhong.util.BeanUtil;
import com.wanhong.util.BusinessBodyConvertUtil;
import com.wanhong.util.FastjsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.xml.ws.Action;
import java.util.List;

/**
 * @author wangmeng247
 * @date 2018-03-03 17:43
 */
public class ApplyController {
    private static final Logger logger = LoggerFactory.getLogger(ApplyController.class);
    @Autowired
    ApplyService applyService;

    @RequestMapping("/getMyApplyInfoByPage")
    @ResponseBody
    public ResultJson<Page<List<ApplyInfo>>> getMyApplyInfoByPage(String body){
        ApplyParam applyParam = BusinessBodyConvertUtil.buildBusinessParam(body,ApplyParam.class);
        Page<List<ApplyInfo>> applyInfoPage =applyService.getMyApplyInfoByPage(applyParam);
        ResultJson<Page<List<ApplyInfo>>> resultJson = new ResultJson<>(BusinessCode.SUCCESS,applyInfoPage);
        logger.info("resultJson:{}", FastjsonUtil.objectToJson(resultJson));
        return resultJson;
    }

    @RequestMapping("/getThireApplyInfoByPage")
    @ResponseBody
    public ResultJson<Page<List<ApplyInfo>>> getThireApplyInfoByPage(String body){
        ApplyParam applyParam = BusinessBodyConvertUtil.buildBusinessParam(body,ApplyParam.class);
        Page<List<ApplyInfo>> applyInfoPage =applyService.getThireApplyInfoByPage(applyParam);
        ResultJson<Page<List<ApplyInfo>>> resultJson = new ResultJson<>(BusinessCode.SUCCESS,applyInfoPage);
        logger.info("resultJson:{}", FastjsonUtil.objectToJson(resultJson));
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
        if (applyService.agreeApply(applyInfo)>0){
            res = true;
        }
        ResultJson<Boolean> resultJson = new ResultJson<>(BusinessCode.SUCCESS,res);
        logger.info("resultJson:{}", FastjsonUtil.objectToJson(resultJson));
        return resultJson;
    }

    @RequestMapping("/refuseApply")
    @ResponseBody
    public ResultJson<Boolean> refuseApply(String body){
        ApplyParam applyParam = BusinessBodyConvertUtil.buildBusinessParam(body,ApplyParam.class);
        ApplyInfo applyInfo = new ApplyInfo();
        BeanUtil.copyProperties(applyParam,applyInfo);
        Boolean res = false;
        if (applyService.refuseApply(applyInfo)>0){
            res = true;
        }
        ResultJson<Boolean> resultJson = new ResultJson<>(BusinessCode.SUCCESS,res);
        logger.info("resultJson:{}", FastjsonUtil.objectToJson(resultJson));
        return resultJson;
    }
}
