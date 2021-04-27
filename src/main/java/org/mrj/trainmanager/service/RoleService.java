package org.mrj.trainmanager.service;

import java.util.List;

/**
 * @作者 s.1228
 * @日期 2021/4/3 19:35
 * @Version 1.0
 */

public interface RoleService {

    /**
     * 根据角色id查询用户角色
     * @param roleId
     * @return 角色名
     */
    String getRole(long roleId);


    /**
     * 根据用户id删除其所有角色
     * @param userId
     */
    void deleteRoleId(Long userId);

    /**
     * 根据用户id和角色id删除单个角色
     * @param userId
     * @param roleId
     */
    void removeRoleId(Long userId, Long roleId);



}
