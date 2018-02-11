package com.wanhong.util;


import com.wanhong.common.errorcode.BusinessCode;
import com.wanhong.common.exception.BusinessException;
import com.wanhong.domain.param.BusinessParam;

/**
 * Soa消息解析工具类
 * <p></p>
 * Created by tanwei3 on 2017/7/12.
 */
public class BusinessBodyConvertUtil {


    public static void validCommonParam(String body){
        if(StringUtil.isBlank(body)){
            throw BusinessException.asBusinessException(BusinessCode.ILLEGAL_ARG_ERROR);
        }
    }

    public static void validCommonParam(String body, String pin){
        validCommonParam(body, pin, null, true, false);
    }

    public static void validCommonParam(String body, String pin, String ip){
        validCommonParam(body, pin, ip, true, true);
    }


    public static void validCommonParam(String body, String pin, String ip, boolean validPin, boolean validIp){
        if(StringUtil.isBlank(body)){
            throw BusinessException.asBusinessException(BusinessCode.ILLEGAL_ARG_ERROR);
        }

        if(validPin && StringUtil.isBlank(pin)){
            throw BusinessException.asBusinessException(BusinessCode.ILLEGAL_ARG_ERROR);
        }

        if(validIp && StringUtil.isBlank(ip)){
            throw BusinessException.asBusinessException(BusinessCode.ILLEGAL_ARG_ERROR);
        }
    }

    public static <T extends BusinessParam> T buildBusinessParam(String body, Class<T> cls) {

        validCommonParam(body);

        T param = FastjsonUtil.jsonToObject(body, cls);

        if (param == null )
            throw BusinessException.asBusinessException(BusinessCode.ILLEGAL_ARG_ERROR);

        return param;
    }

}
