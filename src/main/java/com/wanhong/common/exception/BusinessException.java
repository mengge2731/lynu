package com.wanhong.common.exception;

import com.wanhong.common.errorcode.BusinessCode;
import com.wanhong.common.errorcode.ErrorCode;

/**
 * Created by wangmeng on 2018/2/11.
 */
public class BusinessException extends RuntimeException {

    private ErrorCode errorCode;

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.toString());
        this.errorCode = errorCode;
    }

    public BusinessException(ErrorCode errorCode, String errorMessage) {
        super(errorCode.toString() + " - " + errorMessage);
        this.errorCode = errorCode;
    }

    private BusinessException(ErrorCode errorCode, String errorMessage,
                              Throwable cause) {
        super(errorCode.toString() + " - " + getMessage(errorMessage)
                + " - " + getMessage(cause), cause);

        this.errorCode = errorCode;
    }

    public static BusinessException asBusinessException(ErrorCode errorCode) {
        return new BusinessException(errorCode);
    }

    public static BusinessException asBusinessException(ErrorCode errorCode, String message) {
        return new BusinessException(errorCode, message);
    }

    public static BusinessException asBusinessException(ErrorCode errorCode, String message, Throwable cause) {
        if (cause instanceof BusinessException) {
            return (BusinessException) cause;
        }
        return new BusinessException(errorCode, message, cause);
    }

    public static BusinessException asBusinessException(ErrorCode errorCode, Throwable cause) {
        if (cause instanceof BusinessException) {
            return (BusinessException) cause;
        }
        return new BusinessException(errorCode, null, cause);
    }

    public ErrorCode getErrorCode() {
        return this.errorCode;
    }


    private static String getMessage(Object obj) {
        if (obj == null) {
            return "";
        }

        if (obj instanceof Throwable) {
            return ((Throwable) obj).getMessage();
        } else {
            return obj.toString();
        }
    }

    public static void main(String[] args) {
        RuntimeException r = new RuntimeException("test001!!!");
        BusinessException aa = BusinessException.asBusinessException(BusinessCode.UNKNOWN_ERROR,r);
        System.out.println(aa.getMessage());
    }
}
