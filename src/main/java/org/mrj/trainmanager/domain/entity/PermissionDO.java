package org.mrj.trainmanager.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * @作者 s.1228
 * @日期 2021/3/25 16:59
 * @Version 1.0
 */

@SuperBuilder//继承父类属性
@Data//get、set方法
@AllArgsConstructor//所有的有参构造
@NoArgsConstructor//无参构造
public class PermissionDO extends BaseDO{

    /**
     * 权限名
     */
    private String permission;

    /**
     * 权限id
     */
    private long permissionId;

}
