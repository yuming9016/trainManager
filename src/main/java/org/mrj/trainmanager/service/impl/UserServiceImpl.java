package org.mrj.trainmanager.service.impl;

import cn.hutool.crypto.SecureUtil;
import org.mrj.trainmanager.common.enums.ErrorCodeEnums;
import org.mrj.trainmanager.common.enums.UserStatusEnums;
import org.mrj.trainmanager.common.exception.TrainException;
import org.mrj.trainmanager.domain.PageDAO;
import org.mrj.trainmanager.domain.param.LoginParam;
import org.mrj.trainmanager.domain.dto.UserDTO;
import org.mrj.trainmanager.domain.entity.UserDO;
import org.mrj.trainmanager.domain.mapper.UserEntityMapper;
import org.mrj.trainmanager.domain.param.UserParam;
import org.mrj.trainmanager.mapper.PermissionMapper;
import org.mrj.trainmanager.mapper.RoleMapper;
import org.mrj.trainmanager.mapper.UserMapper;
import org.mrj.trainmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @作者 s.1228
 * @日期 2021/2/24 0:06
 * @Version 1.0
 */

@Service
public class UserServiceImpl implements UserService {

    //注入mapper
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private PermissionMapper permissionMapper;


    @Override
    public UserDO selcet(Long id) {
        UserDO userDO = userMapper.getUserById(id);
        return userDO;
    }

    //通过用户名和密码登录
    @Override
    public UserDTO getUserByNameAndPassword(LoginParam loginParam) {

        // 1. 查user表 根据username 和 paasword 你可以获得UserDO
        // 2. 拿UserDO 中的id 去查user_role表 查出这个用户的 角色id列表 List<Long> √
        // 3. 遍历角色id列表 每次拿出其中一个角色id 查 role_permission表 拿到这个角色的 权限id列表 List<Long>
        // 4. 遍历权限id列表 每次拿出其中一个权限id 查 permssion 表 拿到PermissionDO
        // 5. 把查到的权限实体中的权限码 放入一个Set中 Set会自动去重 这样就可以拿到一个用户的所有权限名 Set<String> 建议把这个Set 放入UserDTO
        // 6. 设计一个接口来判断用户身份信息



        //加密前端传过来的密码
        loginParam.setPassword(SecureUtil.md5(loginParam.getPassword()));

        UserDO user = userMapper.getUserByNameAndPassword(loginParam);

        if (user == null){
            //如果没查到对应user，则抛出异常
            throw new TrainException(ErrorCodeEnums.USERNAME_OR_PASSWORD_ERROR);
        }
        if (UserStatusEnums.FREEZE.getCode().equals(Integer.parseInt(user.getStatus()))) {//判断账号是否被冻结

            throw new TrainException(ErrorCodeEnums.FREEZE);
        }

        //查到了就DO转DTO
        return UserEntityMapper.INSTANCE.userDo2Dto(user);
    }

    //修改用户密码
    @Override
    public void updatePassword(Long id, String newPassword) {

        String password = SecureUtil.md5(newPassword);//加密密码

        userMapper.updatePasswordById(id, password);//通过id和新密码为用户更新密码

    }

    //修改用户冻结状态
    @Override
    public void updateStatus(Long id, String status) {

        userMapper.updateStatusById(id, status);

    }

    //根据id获得用户密码
    @Override
    public boolean checkPassword(Long id, String password) {

        String pwd = SecureUtil.md5(password);//加密用户输入的旧密码

        String oldPassword = userMapper.getPasswordById(id);//获得数据库中的旧密码

        if (oldPassword.equals(pwd)) {//如果用户输入的密码和其旧密码相同则返回true
            return true;
        }else {
            return false;//否则返回false
        }

    }


    //新增用户
    @Override
    public void addUser(UserDTO userDTO) {

        userDTO.setPassword(SecureUtil.md5(userDTO.getPassword()));//设置加密密码
        userMapper.insertUser(UserEntityMapper.INSTANCE.userDto2Do(userDTO));//dto转do
        //查询新建用户的id
        long id = userMapper.getIdByName(userDTO.getName());

        System.out.println("roleid = " + userDTO.getRoleId());

        //如果是注册页面调的增加用户
        if(userDTO.getRoleId()==null) {
            userMapper.insertRoleId(id,1);//为新注册的用户添加默认角色
        }else {//管理员手动添加的用户

            //为新建用户添加角色
            for (int i=0; i<userDTO.getRoleId().size() ; i++) {
                userMapper.insertRoleId(id,userDTO.getRoleId().get(i));
            }
        }

    }


    //检查用户是否已存在
    @Override
    public UserDTO checkUser(UserParam userParam) {

        UserDO user = userMapper.checkUser(userParam);

        if (user != null){
            //如果查到对应user，则抛出异常
            throw new TrainException(ErrorCodeEnums.USER_EXIST);//在异常类新增字段
        }
        return null;
    }

    //查询用户的角色id
    @Override
    public List<Long> getUserRoleId(Long id) {

        List<Long> roleId = userMapper.getRoleIdById(id);

        return roleId;

    }

    //查询所有用户基本信息（为了使用后端分页舍弃了）
    @Override
    public List<UserDTO> getAllUser() {

        List<UserDO> userDOList = userMapper.selectAllUser();//获得所有用户基本信息

        return UserEntityMapper.INSTANCE.userDoList2DtoList(userDOList);
    }

    //删除用户
    @Override
    public void deleteUser(Long id) {

        userMapper.deleteUserById(id);

    }

    //打包所有的用户名、用户id、角色、权限名
    @Override
    public List<UserDTO> getAllUserRolePermission() {

        //获取所有用户的用户id和用户名
        List<UserDTO> userDTOList = UserEntityMapper.INSTANCE.userDoList2DtoList(userMapper.selectAllUser());

        //获取所有用户的角色id列表
        for (int i=0 ; i<userDTOList.size() ; i++) {
            Long id = userDTOList.get(i).getId();//取每个用户的用户id
            List<Long> userRoleIdList = userMapper.getRoleIdById(id);//取每个用户的角色id列表
            userDTOList.get(i).setRoleId(userRoleIdList);//为每一用户添加角色id列表
        }

        //获取所有用户的角色
        for (int i=0 ; i<userDTOList.size() ; i++) {
            List<Long> roleIdList = userDTOList.get(i).getRoleId();//获得每个用户的角色id列表

            List<String> roleList = new ArrayList<>();//创建临时存放角色的list

            for (int y=0 ; y<roleIdList.size() ; y++) {//遍历用户的角色id

                Long roleId = roleIdList.get(y);//获得角色id列表中的单个角色id
                String role = roleMapper.getRoleByRoleId(roleId);//获得角色id所对应的角色
                roleList.add(role);//把该用户角色存入临时list

            }
            userDTOList.get(i).setRole(roleList);//获得用户的所有角色
        }

        //获取所有用户的权限id列表
        for (int i=0 ; i<userDTOList.size() ; i++) {
            List<Long> roleIdList = userDTOList.get(i).getRoleId();//获得每个用户的角色id列表

            List<Long> permissionIdList = new ArrayList<>();//创建临时存放角色权限id的list

            for (int y=0 ; y<roleIdList.size() ; y++) {//遍历用户的角色id

                Long roleId = roleIdList.get(y);//获得角色id列表中的单个角色id
                //获得该用户角色的权限id列表
                permissionIdList = permissionMapper.getPermissionIdByRoleId(roleId);
            }
            userDTOList.get(i).setPermissionId(permissionIdList);//获得该用户的所有权限id
        }

        //获取所有用户的所拥有的权限名
        for (int i=0 ; i<userDTOList.size() ; i++) {
            List<Long> permissionIdList = userDTOList.get(i).getPermissionId();//获得每个用户的权限id表

            Set<String> permissionList = new HashSet<>();//新建临时存放权限名的set
            for (int y=0 ; y<permissionIdList.size() ; y++) {
                Long permissionId = permissionIdList.get(y);//获得单个权限id
                permissionList.add(permissionMapper.getPermissionByPermissionId(permissionId));//获得权限名放入set
            }
            userDTOList.get(i).setPermission(permissionList);//获得该用户的所有权限名
        }


        return userDTOList;//返回打包好的userDTOList
    }

    //新建用户的角色id
    @Override
    public void addUserRoleId(Long id, Long roleId) {
        userMapper.insertRoleId(id, roleId);
    }


    //查询所有用户信息和总数
    @Override
    public PageDAO getAllUserByPage(String name, Integer pageNum, Integer pageSize) {

        if(pageNum!=null&&pageSize!=null){
            pageNum=(pageNum-1)*pageSize;
        }

        Long total = userMapper.getTotal();//获得用户总数

        //获得打包好的userDTO,包括id、用户名、状态、角色列表、角色id列表、权限列表、权限id列表
        //根据条件获取所有用户的用户id和用户名、状态
        List<UserDTO> userDTOList = UserEntityMapper.INSTANCE.userDoList2DtoList(userMapper.selectAllUserByPage(name, pageNum, pageSize));

        //获取所有用户的角色id列表
        for (int i=0 ; i<userDTOList.size() ; i++) {
            Long id = userDTOList.get(i).getId();//取每个用户的用户id
            List<Long> userRoleIdList = userMapper.getRoleIdById(id);//取每个用户的角色id列表
            userDTOList.get(i).setRoleId(userRoleIdList);//为每一用户添加角色id列表
        }

        //获取所有用户的角色
        for (int i=0 ; i<userDTOList.size() ; i++) {
            List<Long> roleIdList = userDTOList.get(i).getRoleId();//获得每个用户的角色id列表

            List<String> roleList = new ArrayList<>();//创建临时存放角色的list

            for (int y=0 ; y<roleIdList.size() ; y++) {//遍历用户的角色id

                Long roleId = roleIdList.get(y);//获得角色id列表中的单个角色id
                String role = roleMapper.getRoleByRoleId(roleId);//获得角色id所对应的角色
                roleList.add(role);//把该用户角色存入临时list

            }
            userDTOList.get(i).setRole(roleList);//获得用户的所有角色
        }

        //获取所有用户的权限id列表
        for (int i=0 ; i<userDTOList.size() ; i++) {
            List<Long> roleIdList = userDTOList.get(i).getRoleId();//获得每个用户的角色id列表

            List<Long> permissionIdList = new ArrayList<>();//创建临时存放角色权限id的list

            for (int y=0 ; y<roleIdList.size() ; y++) {//遍历用户的角色id

                Long roleId = roleIdList.get(y);//获得角色id列表中的单个角色id
                //获得该用户角色的权限id列表
                permissionIdList = permissionMapper.getPermissionIdByRoleId(roleId);
            }
            userDTOList.get(i).setPermissionId(permissionIdList);//获得该用户的所有权限id
        }

        //获取所有用户的所拥有的权限名
        for (int i=0 ; i<userDTOList.size() ; i++) {
            List<Long> permissionIdList = userDTOList.get(i).getPermissionId();//获得每个用户的权限id表

            Set<String> permissionList = new HashSet<>();//新建临时存放权限名的set
            for (int y=0 ; y<permissionIdList.size() ; y++) {
                Long permissionId = permissionIdList.get(y);//获得单个权限id
                permissionList.add(permissionMapper.getPermissionByPermissionId(permissionId));//获得权限名放入set
            }
            userDTOList.get(i).setPermission(permissionList);//获得该用户的所有权限名
        }

        PageDAO pageDAO = new PageDAO();
        pageDAO.setData(userDTOList);
        pageDAO.setTotal(total);

        return pageDAO;
    }

    //根据用户id和角色id列表添加用户角色
    @Override
    public void addUserRoleIdByRoleIdList(Long id, List<Long> roleIdList) {
        for (int i=0; i<roleIdList.size() ; i++) {
            userMapper.insertRoleId(id,roleIdList.get(i));//逐个添加
        }
    }


}
