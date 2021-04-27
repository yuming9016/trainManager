package org.mrj.trainmanager.mapper;

import java.util.List;

/**
 * @作者 s.1228
 * @日期 2021/4/3 19:34
 * @Version 1.0
 */

public interface RoleMapper {

    /**
     * 通过角色id获取用户角色
     * @param roleId 用户id
     * @return 角色id
     */
    String getRoleByRoleId(long roleId);

    /**
     * 根据用户id删除其所有角色
     * @param userId 用户id
     */
    void deleteRoleIdByUserId(Long userId);

    /**
     * 根据用户id和角色id删除单个角色
     * @param userId 用户id
     * @param roleId 角色id
     */
    void removeRoleIdByUserId(Long userId, Long roleId);

}
