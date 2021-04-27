package org.mrj.trainmanager.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @作者 s.1228
 * @日期 2021/3/1 22:36
 * @Version 1.0
 */
@Data//get、set方法
@AllArgsConstructor//所有的有参构造
@NoArgsConstructor//所有的无参构造
public class UserVO implements Serializable {

    /**
     * id
     */
    private long id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 状态
     */
    private Integer status;

    /**
     * token令牌
     */
    private String token;

    /**
     * 角色
     */
    private List<String> role;

    /**
     * 角色id
     */
    private List<Long> roleId;

    /**
     * 权限名
     */
    private Set<String> permission;

    /**
     * 权限id
     */
    private List<Long> permissionId;
}
