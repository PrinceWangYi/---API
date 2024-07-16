package com.prince.blog_system.exception;

public enum BlogExceptionEnum {

    SUCCESS("200","SUCCESS"),
    ERROR("500","未知的系统错误，请联系管理员"),
    USER_LOGIN_FAILED("9991","用户登录失败"),
    VALID_LOGIN_PASSWORD("9992","两次密码输入不一致"),
    DUPLICATION_ACCOUNT("9993","账户已存在"),
    VALID_LOGIN_USER("9994","用户未登录"),
    INSERT_FAILED("9995","插入信息失败"),
    NULL_ART_LIST("9996","文章对应ID不存在"),
    TOKEN_EXCEPTION("2001","对不起，您还未登录"),
    TOKEN_INVALID("2002","token非法"),
    TOKEN_NULL("2012","token为空"),
    UPDATE_INVALID("2003","更新信息失败"),
    DELETE_INVALID("2004","删除信息失败"),
    ONLY_SELF_UPDATE("2005","只能修改自己的信息")
    ;
    private String code;
    private String msg;

    BlogExceptionEnum(String code, String msg) {
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
