package org.mrj.trainmanager.service.impl;

import org.mrj.trainmanager.mapper.RoleMapper;
import org.mrj.trainmanager.mapper.UserMapper;
import org.mrj.trainmanager.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

/**
 * @作者 s.1228
 * @日期 2021/4/3 19:36
 * @Version 1.0
 */

@Service
public class RoleServiceImpl implements RoleService {

    //注入mapper
    @Autowired
    private RoleMapper roleMapper;



    //查询角色
    @Override
    public String getRole(long roleId) {

        String role = roleMapper.getRoleByRoleId(roleId);

        return role;

    }




    //删除用户的所有角色id
    @Override
    public void deleteRoleId(Long userId) {
        roleMapper.deleteRoleIdByUserId(userId);
    }

    //删除用户的单个角色id
    @Override
    public void removeRoleId(Long userId, Long roleId) {
        roleMapper.removeRoleIdByUserId(userId, roleId);
    }
}
