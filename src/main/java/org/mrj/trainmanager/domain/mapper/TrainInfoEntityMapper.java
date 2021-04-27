package org.mrj.trainmanager.domain.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.mrj.trainmanager.domain.dto.TrainInfoDTO;
import org.mrj.trainmanager.domain.entity.TrainInfoDO;
import org.mrj.trainmanager.domain.param.TrainInfoParam;
import org.mrj.trainmanager.domain.vo.TrainInfoVO;

import java.util.List;

/**
 * @作者 s.1228
 * @日期 2021/4/9 14:30
 * @Version 1.0
 */

@Mapper
public interface TrainInfoEntityMapper {

    //    Mappers.getMapper方法会帮我们自动构造TrainEntityMapperImpl实现类
//    这样一来，我们就可以通过TrainDO.INSTANCE.trainDo2Dto(trainDO)来使我们的DO向DTO转化了
    TrainInfoEntityMapper INSTANCE = Mappers.getMapper(TrainInfoEntityMapper.class);

    TrainInfoDTO trainInfoDo2Dto(TrainInfoDO trainInfoDO);

    TrainInfoDO trainInfoDto2Do(TrainInfoDTO trainInfoDTO);

    TrainInfoDTO trainInfoParam2Dto(TrainInfoParam trainInfoParam);

    List<TrainInfoDTO> trainInfoDoList2DtoList(List<TrainInfoDO> trainInfoDOList);

    TrainInfoVO trainInfoDto2Vo(TrainInfoDTO trainInfoDTO);

    List<TrainInfoVO> trainInfoDtoList2VoList(List<TrainInfoDTO> trainInfoDTOList);

}
