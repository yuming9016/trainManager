package org.mrj.trainmanager.common.enums;

/**
 * @作者 s.1228
 * @日期 2021/3/21 23:03
 * @Version 1.0
 */

public enum  UserStatusEnums {

    FREEZE(1,"冻结"),
    NORMAL(0,"正常")
    ;


    private Integer code;

    private String desc;


    UserStatusEnums(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }
}
