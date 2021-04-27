package org.mrj.trainmanager.service.impl;


import org.mrj.trainmanager.mapper.PermissionMapper;
import org.mrj.trainmanager.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @作者 s.1228
 * @日期 2021/4/4 18:45
 * @Version 1.0
 */

@Service
public class PermissionServiceImpl implements PermissionService {

    //注入mapper
    @Autowired
    private PermissionMapper permissionMapper;

    //查询权限id
    @Override
    public List<Long> getPermissionId(long roleId) {

        List<Long> permissionId = permissionMapper.getPermissionIdByRoleId(roleId);

        return permissionId;
    }

    //查询权限名
    @Override
    public String getPermission(long permissionId) {

        String permission = permissionMapper.getPermissionByPermissionId(permissionId);

        return permission;
    }
}
