package org.mrj.trainmanager.domain.param;


import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @作者 s.1228
 * @日期 2021/3/1 22:37
 * @Version 1.0
 */
@Data//get、set方法
public class LoginParam {


    /**
     * 姓名
     */
    @NotNull
    private String name;

    /**
     * 密码
     */
    @NotNull
    private String password;

    /**
     * token令牌
     */
    private String token;



}
