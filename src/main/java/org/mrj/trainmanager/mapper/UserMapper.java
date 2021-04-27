package org.mrj.trainmanager.mapper;

import org.mrj.trainmanager.domain.dto.TrainDTO;
import org.mrj.trainmanager.domain.dto.UserDTO;
import org.mrj.trainmanager.domain.param.LoginParam;
import org.mrj.trainmanager.domain.entity.UserDO;
import org.mrj.trainmanager.domain.param.UserParam;
import org.mrj.trainmanager.domain.vo.UserVO;

import java.util.List;


/**
 * @作者 s.1228
 * @日期 2021/2/23 23:56
 * @Version 1.0
 */

public interface UserMapper {
    /**
     * 通过id获取用户信息（测试）
     * @param id 用户名
     * @return 用户信息
     */
    UserDO getUserById(Long id);

    /**
     * 通过id获取用户信息
     * @param loginParam 传入参数
     * @return 账号内容
     */
    UserDO getUserByNameAndPassword(LoginParam loginParam);

    /**
     * 通过id修改用户密码
     * @param id 用户id
     * @param password 新密码
     */
    void updatePasswordById(Long id, String password);

    /**
     * 通过id修改用户状态
     * @param id 用户id
     * @param status 状态
     */
    void updateStatusById(Long id, String status);


    /**
     * 通过id获取用户密码
     * @param id 用户名
     * @return 用户密码
     */
    String getPasswordById(Long id);

    /**
     * 新增用户
     * @param userDO 用户内容
     */
    void insertUser(UserDO userDO);

    /**
     * 通过用户名获取用户id
     * @param name 用户名
     * @return 用户id
     */
    long getIdByName(String name);

    /**
     * 根据用户id新建其角色id
     * @param id 用户id
     * @param roleId 角色id
     */
    void insertRoleId(long id, long roleId);

    /**
     * 检查用户
     * @param userParam 用户内容
     */
    UserDO checkUser(UserParam userParam);

    /**
     * 通过id获取用户角色
     * @param id 用户id
     * @return 角色id
     */
    List<Long> getRoleIdById(Long id);

    /**
     * 获取所有用户基本信息
     * @return 所有用户基本信息
     */
    List<UserDO> selectAllUser();

    /**
     * 根据用户id删除用户
     * @param id 用户内容
     */
    void deleteUserById(Long id);

    /**
     * 获取所有用户基本信息
     * @return 所有用户基本信息
     */
    List<UserDO> selectAllUserByPage(String name, Integer pageNum, Integer pageSize);

    /**
     * 获取所有用户基本信息的总数
     * @return 总数
     */
    Long getTotal();
}
