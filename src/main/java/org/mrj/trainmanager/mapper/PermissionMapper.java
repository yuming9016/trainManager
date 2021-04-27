package org.mrj.trainmanager.mapper;

import java.util.List;

/**
 * @作者 s.1228
 * @日期 2021/4/4 19:33
 * @Version 1.0
 */

public interface PermissionMapper {
    /**
     * 通过角色id获取权限id
     * @param roleId 用户id
     * @return 权限id
     */
    List<Long> getPermissionIdByRoleId(long roleId);

    /**
     * 通过权限id获取权限名
     * @param permissionId 用户id
     * @return 权限名
     */
    String getPermissionByPermissionId(long permissionId);

}
