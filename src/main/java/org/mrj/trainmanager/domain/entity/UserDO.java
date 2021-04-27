package org.mrj.trainmanager.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

/**
 * @作者 s.1228
 * @日期 2021/2/23 23:50
 * @Version 1.0
 */
@SuperBuilder//继承父类属性
@Data//get、set方法
@AllArgsConstructor//所有的有参构造
@NoArgsConstructor//无参构造
public class UserDO extends BaseDO{

    /**
     * 姓名
     */
    private String name;

    /**
     * 密码
     */
    private String password;

    /**
     * 状态/权限
     */
    private String status;

}
