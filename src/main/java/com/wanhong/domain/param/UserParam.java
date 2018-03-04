package com.wanhong.domain.param;

/**
 * @author wangmeng247
 * @date 2018-02-11 15:39
 */
public class UserParam extends BusinessParam {
    //手机号
    private String phone;
    private String validCode;
    private String message;
    //密码
    private String password;
    //确认密码
    private String ensurePassword;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getValidCode() {
        return validCode;
    }

    public void setValidCode(String validCode) {
        this.validCode = validCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEnsurePassword() {
        return ensurePassword;
    }

    public void setEnsurePassword(String ensurePassword) {
        this.ensurePassword = ensurePassword;
    }
}
