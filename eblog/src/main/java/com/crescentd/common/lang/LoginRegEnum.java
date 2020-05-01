package com.crescentd.common.lang;


/**
 * @author dyh
 * @description:
 * @date 2020/5/1
 **/
public enum LoginRegEnum {
    UnknownAccount("用户不存在","501"),
    IncorrectCredentials("密码错误","502"),
    LockedAccount("用户被禁用","503"),
    AuthFailed("用户认证失败","504");
    private String msg;
    private String code;

    LoginRegEnum(String msg, String code) {
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
