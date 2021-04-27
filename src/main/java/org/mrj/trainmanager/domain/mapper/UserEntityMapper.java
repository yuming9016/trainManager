package org.mrj.trainmanager.domain.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.mrj.trainmanager.domain.param.UserParam;
import org.mrj.trainmanager.domain.dto.UserDTO;
import org.mrj.trainmanager.domain.entity.UserDO;
import org.mrj.trainmanager.domain.vo.UserVO;

import java.util.List;

/**
 * @作者 s.1228
 * @日期 2021/3/1 22:39
 * @Version 1.0
 */

@Mapper
public interface UserEntityMapper {

//    Mappers.getMapper方法会帮我们自动构造UserEntityMapperImpl实现类
//    这样一来，我们就可以通过userDO.INSTANCE.userDo2Dto(userDO)来使我们的DO向DTO转化了
    UserEntityMapper INSTANCE = Mappers.getMapper(UserEntityMapper.class);

    UserDTO userDo2Dto(UserDO userDO);

    UserDO userDto2Do(UserDTO userDTO);

    UserVO userDto2Vo(UserDTO userDTO);


    UserDTO userParam2Dto(UserParam userParam);

    List<UserDTO> userDoList2DtoList(List<UserDO> userDOList);

    List<UserVO> userDtoList2VoList(List<UserDTO> userDTOList);

    List<UserDO> userDtoList2DoList(List<UserDTO> userDTOList);

}
