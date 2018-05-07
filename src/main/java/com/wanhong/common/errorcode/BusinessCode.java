package com.wanhong.common.errorcode;

/**
 * @author wangmeng247
 * @date 2018-02-08 17:02
 */
public enum BusinessCode implements ErrorCode{
    SUCCESS("0000", "请求成功."),
    NOT_LOG_IN("0001", "未登录，请先登录"),
    IS_LOG_IN("0002", "已登录"),
    ILLEGAL_ARG_ERROR("0003", "请求参数错误."),
    UNKNOWN_ERROR("0004", "未知异常."),
    SEND_MESSAGE_ERROR("0005", "发送短信异常."),
    SEND_MESSAGE_SUCCESS("0006", "发送短信成功."),
    DIFFERENT_PASSWORD_ERROR("0007", "两次密码不一致."),
    MESSAGE_PHONE_ERROR("2","手机格式不正确或者手机不能为空!"),
    MESSAGE_TOO_MUCH_ERROR("22","1小时内最多只能发送3条验证码，每条有效时间为20分钟！您可能已经超限，无法继续发送！请等待超时后再试！"),
    MESSAGE_FREQUENTLY_ERROR("33","超过频率，同一个手机号每30秒只能发送一条"),
    MESSAGE_WRONG("0008", "消息错误."),
    MESSAGE_OVER_TIME("0009", "消息超时."),
    APPLY_OWN_DATA("0010", "不能申请自己发布的数据."),
    UPDATE_ERROR("0011", "更新失败."),
    REGISTER_ERROR("0012", "注册失败."),
    REGISTER_AGINE("0013", "已经注册过，请直接登录."),
    LOGIN_ERROR("0014", "账号或密码错误."),
    APPLY_AGINE_ERROR("0015", "已经申请过该数据."),
    NO_ACCESS("0016", "没有权限修改数据."),
    GET_FILE_ERROR("0017", "获取文件数据出错,确认是否选中文件."),
    FILE_SIZE_ERROR("0018", "大小超出限制，不能超过1G."),
    FILE_UPLOAD_ERROR("0019", "保存上传的图片失败."),
    SEND_AGINE("0020", "已经发过短信."),
    FORBIDDEN("0021", "无权访问."),
    USER_STATUS_ERROR("0022", "用户状态异常，请联系系统管理员."),
    APPLY_DELETE_ERROR("0023", "请先同意或者拒绝后再删除该条数据."),
    LOG_OUT("0024", "退出登录."),
    ;
    private final String code;
    private final String description;

    BusinessCode(String code,String description){
        this.code = code;
        this.description = description;
    }

    public String getStringCode() {
        return this.code;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getDescription() {
        return this.description;
    }


    @Override
    public String toString() {
        return String.format("Code:[%s], Description:[%s]. ", this.code, this.description);
    }

}
