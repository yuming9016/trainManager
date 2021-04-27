package org.mrj.trainmanager.service;

import java.util.List;

/**
 * @作者 s.1228
 * @日期 2021/4/3 22:28
 * @Version 1.0
 */

public interface PermissionService {

    /**
     * 根据roleId查询权限id
     * @param roleId
     * @return 权限id
     */
    List<Long> getPermissionId(long roleId);

    /**
     * 根据权限Id查询权限名
     * @param permissionId
     * @return 权限名
     */
    String getPermission(long permissionId);

}
