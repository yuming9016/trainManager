package org.mrj.trainmanager.controller;

import org.mrj.trainmanager.common.enums.ErrorCodeEnums;
import org.mrj.trainmanager.common.exception.TrainException;
import org.mrj.trainmanager.common.util.JwtUtils;

import org.mrj.trainmanager.domain.PageDAO;
import org.mrj.trainmanager.domain.entity.RoleDO;
import org.mrj.trainmanager.domain.mapper.UserEntityMapper;
import org.mrj.trainmanager.domain.param.*;
import org.mrj.trainmanager.domain.dto.UserDTO;
import org.mrj.trainmanager.domain.entity.UserDO;

import org.mrj.trainmanager.domain.vo.UserVO;
import org.mrj.trainmanager.response.Response;
import org.mrj.trainmanager.service.PermissionService;
import org.mrj.trainmanager.service.RoleService;
import org.mrj.trainmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @作者 s.1228
 * @日期 2021/2/24 0:10
 * @Version 1.0
 */

@RestController
@RequestMapping(value = "/trainmanager")//浏览器地址为localhost：8081/trainmanager/什么什么。。。。。
public class UserController {



    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;


    //输入localhost:8080/trainmanager/testselect？id=1   就可以查询id为1的user信息
    @RequestMapping(value = "/testselect",method = RequestMethod.GET)
    @ResponseBody
    public Response testselect(String id) {
        System.out.println("id = " + id);
        UserDO userDO = userService.selcet(Long.parseLong(id));//获得用户名、状态

        UserDTO userDTO = UserEntityMapper.INSTANCE.userDo2Dto(userDO);

        userDTO.setRoleId(userService.getUserRoleId(Long.parseLong(id)));//获得角色id列表

        //根据角色id获得角色列表
        List<Long> userRoleId = userDTO.getRoleId();//打包用户的角色id
        List<String> role = new ArrayList<>();//临时变量，用来存用户角色列表
        for (int i = 0 ; i < userRoleId.size() ; i++) {//逐条根据角色id获得角色，并加入到临时表中

            role.add(roleService.getRole(userRoleId.get(i)));

        }
        userDTO.setRole(role);

        return Response.success(userDTO);
    }

    //登录
    //设定登录事件，这样前端就可以发http请求来访问这个登录接口
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public Response login(@RequestBody @Valid LoginParam loginParam){
        //   @RequestBody主要用来接收请求体中的数据的，@RequestBody接收数据时，前端不能使用GET方式提交数据，而是用POST方式进行提交。

        UserDTO user = userService.getUserByNameAndPassword(loginParam);


        UserVO userVO = new UserVO();
        userVO.setId(user.getId());
        userVO.setName(user.getName());
        userVO.setStatus(user.getStatus());
        //根据用户id和name生成token
        String id = String.valueOf(user.getId());
        String jwtToken = JwtUtils.getJwtToken(id, user.getName());

        userVO.setToken(jwtToken);//添加token字符串

        //根据用户id获得用户的角色ID列表
        userVO.setRoleId(userService.getUserRoleId(userVO.getId()));

        //根据角色id获得角色列表
        List<Long> userRoleId = userVO.getRoleId();//打包用户的角色id
        List<String> role = new ArrayList<>();//临时变量，用来存用户角色列表
        for (int i = 0 ; i < userRoleId.size() ; i++) {//逐条根据角色id获得角色，并加入到临时表中

            role.add(roleService.getRole(userRoleId.get(i)));

        }
        userVO.setRole(role);

        //根据角色id获得权限id列表
        List<Long> userPermissionId = new ArrayList<>();//用于打包用户角色的权限id
        for (int i = 0 ; i < userRoleId.size() ; i++) {//逐条根据角色id获得权限id列表

            long roleId = userRoleId.get(i);//取用户的一角色id

            List<Long> permissionId = permissionService.getPermissionId(roleId);//通过角色id查权限id列表，用临时变量来保存

            for (int y = 0 ; y < permissionId.size() ; y++) {
                userPermissionId.add(permissionId.get(y));//把角色对应的权限id列表都放到用户角色的权限id表中
            }
        }
        userVO.setPermissionId(userPermissionId);//把权限id传给userVO


        //根据权限id列表获得权限名列表
        Set<String> userPermission = new HashSet<>();;//用于打包权限名列表
        for (int i = 0 ; i < userPermissionId.size() ; i++ ) {

            long permissionId = userPermissionId.get(i);//取一权限id

            String permission = permissionService.getPermission(permissionId);//获得权限id所对应的权限名

            userPermission.add(permission);
        }
        userVO.setPermission(userPermission);//把权限名传给userVO



        return Response.success(userVO);
    }

    //注册（增加用户）
    @RequestMapping(value = "/addUser",method = RequestMethod.POST)
    @ResponseBody
    public Response addUser(@RequestBody @Valid UserParam userParam){
        System.out.println("userParam = " + userParam);
        /*userService.checkUser(userParam);//判断用户是否存在*/

        UserDTO userDTO = UserEntityMapper.INSTANCE.userParam2Dto(userParam);//把前端传过来的param转DTO
        //userDTO.setStatus(0);//将初始用户状态设置为正常



        userService.addUser(userDTO);//调用新增用户方法
        return Response.success(true);
    }

    //判断用户名是否重复
    @RequestMapping(value = "/checkUser",method = RequestMethod.POST)
    @ResponseBody
    public Response checkUser(@RequestBody @Valid UserParam userParam) {
        userService.checkUser(userParam);
        return Response.success(true);
    }

    //修改用户密码
    @RequestMapping(value = "/updatePassword",method = RequestMethod.POST)
    @ResponseBody
    public Response updatePassword(@RequestBody @Valid UpdatePasswordParam param,String id) {
                                                //这上面两个注解必须跟在Param前面

        boolean flag = userService.checkPassword(Long.parseLong(id), param.getPassword());//先检查用户输入的密码是否和旧密码相同

        if (flag) {//相同则修改密码
            userService.updatePassword(Long.parseLong(id), param.getNewPassword());
        }else {
            throw new TrainException(ErrorCodeEnums.PASSWORD_ERROR);
        }

        return Response.success(true);

    }

    //查询所有用户（为了使用后端分页舍弃了）
    @RequestMapping(value = "/selectAllUser",method = RequestMethod.POST)
    @ResponseBody
    public Response selectAllUser() {

        List<UserDTO> userDTOS = userService.getAllUser();

        List<UserDO> userDOS = UserEntityMapper.INSTANCE.userDtoList2DoList(userDTOS);

        return Response.success(userDOS);

    }

    //修改用户是否被冻结信息、角色id
    @RequestMapping(value = "/updateUserInfo",method = RequestMethod.POST)
    @ResponseBody
    public Response updateUserInfo(@RequestBody @Valid UserParam param, String id) {
                                    //这上面两个注解必须跟在Param前面
        //通过id修改用户状态
        userService.updateStatus(Long.parseLong(id), param.getStatus());

        //通过id删除用户的所有角色id列表
        roleService.deleteRoleId(Long.parseLong(id));
        //通过param添加用户的角色id列表
        userService.addUserRoleIdByRoleIdList(Long.parseLong(id), param.getRoleId());


        return Response.success(true);

    }

    //删除用户
    @RequestMapping(value = "/removeUser",method = RequestMethod.POST)
    @ResponseBody
    public Response removeUser(Long id) {

        userService.deleteUser(id);//删除用户表中的信息

        roleService.deleteRoleId(id);//删除该用户的所有角色

        return Response.success(true);

    }

    //查询所有用户的用户id、用户名、角色、权限名
    @RequestMapping(value = "/selectAllUserRolePermission",method = RequestMethod.POST)
    @ResponseBody
    public Response selectAllUserRolePermission() {

        //将打包好的对象转VOList
        List<UserVO> userVOList = UserEntityMapper.INSTANCE.userDtoList2VoList(userService.getAllUserRolePermission());

        return Response.success(userVOList);

    }

    //新增用户的角色id
    @RequestMapping(value = "/addUserRoleId",method = RequestMethod.POST)
    @ResponseBody
    public Response addUserRoleId(@RequestBody @Valid UserRoleIdParam param){

        userService.addUserRoleId(Long.parseLong(param.getId()) , Long.parseLong(param.getRoleId()));
        return Response.success(true);
    }

    //删除用户的单个角色
    @RequestMapping(value = "/removeUserRoleId",method = RequestMethod.POST)
    @ResponseBody
    public Response removeUserRoleId(@RequestBody @Valid UserRoleIdParam param) {

        roleService.removeRoleId(Long.parseLong(param.getId()),Long.parseLong(param.getRoleId()));

        return Response.success(true);

    }

    //查询所有用户
    @RequestMapping(value = "/selectAllUserByPage",method = RequestMethod.POST)
    @ResponseBody
    public Response selectAllUserByPage(@RequestBody @Valid PageDAO pageDAO) {

        return Response.success(userService.getAllUserByPage(pageDAO.getName(),pageDAO.getPageNum(),pageDAO.getPageSize()));

    }


}
