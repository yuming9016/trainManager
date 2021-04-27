package org.mrj.trainmanager.service;

import org.mrj.trainmanager.domain.PageDAO;
import org.mrj.trainmanager.domain.param.LoginParam;
import org.mrj.trainmanager.domain.dto.UserDTO;
import org.mrj.trainmanager.domain.entity.UserDO;
import org.mrj.trainmanager.domain.param.UserParam;

import java.util.List;

/**
 * @作者 s.1228
 * @日期 2021/2/24 0:03
 * @Version 1.0
 */

public interface UserService {

    /**
     * 根据id查询user表的信息
     * @param id
     * @return UserDO
     */
    UserDO selcet(Long id);

    /**
     * 登录
     * @param loginParam 用户名和密码
     * @return 用户信息
     */
    UserDTO getUserByNameAndPassword(LoginParam loginParam);

    /**
     * 根据id修改用户密码
     * @param id
     */
    void updatePassword(Long id, String newPassword);

    /**
     * 根据id修改用户冻结状态
     * @param id
     */
    void updateStatus(Long id, String status);

    /**
     * 根据id检查用户密码
     * @param id
     * @param password 当前用户输入的密码
     */
    boolean checkPassword(Long id, String password);

    /**
     * 新增用户
     * @param userDTO 新增内容
     */
    void addUser(UserDTO userDTO);

    /**
     * 检查用户是否存在
     * @param userParam 新增内容
     */
    UserDTO checkUser(UserParam userParam);

    /**
     * 根据id查询用户角色id
     * @param id
     * @return 角色id
     */
    List<Long> getUserRoleId(Long id);

    /**
     * 获得user表的用户基本信息
     * @return UserDTO
     */
    List<UserDTO> getAllUser();/*（为了使用后端分页舍弃了）*/

    /**
     * 根据id删除用户
     * @param id
     */
    void deleteUser(Long id);

    /**
     * 打包所有的用户名、用户id、角色、权限名
     * @return UserDTO
     */
    List<UserDTO> getAllUserRolePermission();

    /**
     * 根据用户id和角色id新建用户角色
     * @param id 用户id
     * @param roleId 角色id
     */
    void addUserRoleId(Long id, Long roleId);

    /**
     * 获得user表的用户基本信息和总数
     * @return PageDAO
     */
    PageDAO getAllUserByPage(String name, Integer pageNum, Integer pageSize);

    /**
     * 根据用户id和角色id列表添加用户角色
     * @param id 用户id
     * @param roleIdList 角色id列表
     */
    void addUserRoleIdByRoleIdList(Long id, List<Long> roleIdList);
}
