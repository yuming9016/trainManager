package org.mrj.trainmanager.common.enums;

import org.mrj.trainmanager.common.exception.IexceptionCode;

public enum ErrorCodeEnums implements IexceptionCode {

    /**
     * 用户名或密码错误
     */
    USERNAME_OR_PASSWORD_ERROR(1000,"用户名或密码错误!"),
    /**
     * 参数异常
     */
    PARAM(1001, "参数异常"),
    /**
     * 服务器内部错误
     */
    SERVER_INTERVAL_EXCEPTION(1002, "内部服务异常"),
    /**
     * 系统繁忙
     */
    SYSTEM_BUSY(1003, "系统繁忙！"),
    /**
     * 鉴权无权限
     */
    AUTH_FAIL(403, "无权限"),
    /**
     * 参数不能为空
     */
    PARAM_CAN_NOT_BE_NULL(1004, "参数不能为空"),
    /**
     * 账号冻结
     */
    FREEZE(1005, "您的账号已被冻结"),
    /**
     * 用户未登录
     */
    USER_NOT_LOGIN(10000,"用户未登录!"),
    /**
     * 车站不存在
     */
    STATION_NAME_NOT_EXIST(1006,"车站名称不存在"),
    /**
     * 车次不存在
     */
    TRAIN_NUMBER_NOT_EXIST(1007,"车次不存在!"),
    /**
     * 用户已存在
     */
    USER_EXIST(1008,"用户已存在!"),
    /**
     * 密码不正确
     */
    PASSWORD_ERROR(1009,"密码不正确!"),
    /**
     * 火车名称不存在
     */
    TRAIN_NAME_NOT_EXIST(1010,"火车名称不存在!"),
    /**
     * 火车名称已存在
     */
    TRAIN_NAME_EXIST(1011,"火车名称已存在!"),
    /**
     * 车站名称已存在
     */
    STATION_NAME_EXIST(1012,"车站名称已存在!"),

    ;
    private int code;
    private String msg;

    ErrorCodeEnums(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }
}
